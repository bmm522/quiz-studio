package com.jobseeckerstudio.bmm522.encryption;

import com.jobseeckerstudio.bmm522.global.exception.DecryptionException;
import com.jobseeckerstudio.bmm522.user.encryption.Decryptor;
import com.jobseeckerstudio.bmm522.user.encryption.Encryptor;
import com.jobseeckerstudio.bmm522.user.encryption.properties.EncryptionProperties;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptorTest {
    private static final String KEY = EncryptionProperties.KEY;
    private static final String ALGORITHM = "AES";

    @Test
    @DisplayName("암호화 테스트 - 성공")
    void testEncrypt_success() {
        // given
        String plainText = "test string";

        // when
        String encryptedText = Encryptor.encrypt("test string");

        // then
        assertNotEquals(encryptedText, plainText);
    }

    @Test
    @DisplayName("복호화 테스트 - 성공")
    void testDecrypt_success() {
        // given
        String plainText = "test string";
        String encryptedText = Encryptor.encrypt(plainText);

        // when
        String decryptedText = Decryptor.decrypt(encryptedText);

        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("복호화 테스트 - 예외 발생")
    void testDecrypt_failure() {
        // given
        String encryptedText = "invalid-encrypted-string";

        // when and then
        assertThrows(DecryptionException.class, () -> Decryptor.decrypt(encryptedText));
    }
}
