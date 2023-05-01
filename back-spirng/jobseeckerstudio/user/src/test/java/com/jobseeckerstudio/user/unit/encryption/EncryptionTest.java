package com.jobseeckerstudio.user.unit.encryption;

import com.jobseeckerstudio.user.encryption.Encryptor;
import com.jobseeckerstudio.user.exception.EncryptionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EncryptionTest {

    @Test
    void testEncrypt() {
        String plaintext = "test";
        String ciphertext = Encryptor.encrypt(plaintext);

        assertThat(ciphertext).isNotEmpty();
        assertThat(ciphertext).isNotEqualTo(plaintext);
    }

    @Test
    void testEncryptWithNullInput() {
        assertThatThrownBy(() -> Encryptor.encrypt(null))
            .isInstanceOf(EncryptionException.class);
    }
}
