//package com.jobseeckerstudio.user.encryption;
//
//import com.jobseeckerstudio.user.exception.DecryptionException;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Value;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class EncryptorTest {
//    @Value("${encryption.secret}")
//    protected static String KEY;
//    private static final String ALGORITHM = "AES";
//
//    @Test
//    @DisplayName("암호화 테스트 - 성공")
//    void testEncrypt_success() {
//        // given
//        String plainText = "test string";
//
//        // when
//        String encryptedText = Encryptor.encrypt("test string");
//
//        // then
//        assertNotEquals(encryptedText, plainText);
//    }
//
//    @Test
//    @DisplayName("복호화 테스트 - 성공")
//    void testDecrypt_success() {
//        // given
//        String plainText = "test string";
//        String encryptedText = Encryptor.encrypt(plainText);
//
//        // when
//        String decryptedText = Decryptor.decrypt(encryptedText);
//
//        // then
//        assertEquals(plainText, decryptedText);
//    }
//
//    @Test
//    @DisplayName("복호화 테스트 - 예외 발생")
//    void testDecrypt_failure() {
//        // given
//        String encryptedText = "invalid-encrypted-string";
//
//        // when and then
//        assertThrows(DecryptionException.class, () -> Decryptor.decrypt(encryptedText));
//    }
//}
