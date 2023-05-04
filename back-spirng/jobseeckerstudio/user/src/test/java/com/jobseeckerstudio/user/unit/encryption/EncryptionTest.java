package com.jobseeckerstudio.user.unit.encryption;

import com.jobseeckerstudio.user.encrypt.Decryptor;
import com.jobseeckerstudio.user.encrypt.Encryptor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("암호화, 복호화 테스트")
@SpringBootTest
@TestPropertySource(locations = "classpath:application-dev.yml")
public class EncryptionTest {

    @Test
    @DisplayName("Decryptor 테스트")
    void testDecrypt() {
        String plainText = "test";
        String encryptText = Encryptor.encrypt(plainText);

        String decryptText = Decryptor.decrypt(encryptText);

        assertThat(plainText).isEqualTo(decryptText);
    }
    @Test
    @DisplayName("Encryptor 테스트")
    void testEncrypt() {
        String plaintext = "test";
        String ciphertext = Encryptor.encrypt(plaintext);

        assertThat(ciphertext).isNotEmpty();
        assertThat(ciphertext).isNotEqualTo(plaintext);
    }

//    @Test
//    void testEncryptWithNullInput() {
//        assertThatThrownBy(() -> Encryptor.encrypt(null))
//            .isInstanceOf(EncryptionException.class);
//    }
}
