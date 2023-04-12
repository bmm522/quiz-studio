package com.jobseeckerstudio.bmm522.encryption;

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

}
