package com.jobseeckerstudio.bmm522.encryption;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Encryptor.class})
@ComponentScan("com.jobseeckerstudio.bmm522.user.encryption")
public class EncryptorTest {



    @Autowired
    private Encryptor encryptor;
    @Test
    @DisplayName("암호화, 복호화 테스트 - 성공")
    void testEncryptDecrypt_success() {
        // given
        String plainText = "test string";

        // when
        String encryptedText = encryptor.encrypt(plainText);
        String decryptedText = encryptor.decrypt(encryptedText);

        // then
        assertEquals(plainText, decryptedText);
    }

//    @Test
//    @DisplayName("잘못된 비밀키로 복호화할 때 예외 발생")
//    void testDecrypt_wrongKey() {
//        // given
//        String plainText = "test string";
//        String encryptedText = encryptor.encrypt(plainText);
//        String wrongKey = "wrongkey123";
//
//        // when & then
//        assertThrows(RuntimeException.class, () -> {
//            encryptor.decrypt(wrongKey, wrongKey);
//        });
//    }
}
