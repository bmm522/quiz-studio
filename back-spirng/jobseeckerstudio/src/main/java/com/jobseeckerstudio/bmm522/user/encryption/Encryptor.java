package com.jobseeckerstudio.bmm522.user.encryption;

import com.jobseeckerstudio.bmm522.user.encryption.properties.EncryptionProperties;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class Encryptor {

    private static final String ALGORITHM = "AES";
    private static final String KEY = EncryptionProperties.KEY; // 대칭키

    public String encrypt(String str)  {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch(Exception e) {
            throw  new RuntimeException("암호화 에러");
        }

    }

    public String decrypt(String encryptedStr)  {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedStr.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String str = new String(decryptedBytes, StandardCharsets.UTF_8);
            return str;
        } catch (Exception e) {
            throw new RuntimeException("복호화 에러");
        }

    }

}
