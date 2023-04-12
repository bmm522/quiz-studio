package com.jobseeckerstudio.bmm522.user.encryption;

import com.jobseeckerstudio.bmm522.global.exception.DecryptionException;
import com.jobseeckerstudio.bmm522.global.exception.EncryptionException;
import com.jobseeckerstudio.bmm522.user.encryption.properties.EncryptionProperties;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.websocket.DecodeException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encryptor extends Crypto{

    public static String encrypt(String str)  {
        try {
            Cipher cipher = getCipher(ALGORITHM, Cipher.ENCRYPT_MODE);
            byte[] encryptedBytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch(Exception e) {
            throw  new EncryptionException(e.getMessage());
        }

    }

}
