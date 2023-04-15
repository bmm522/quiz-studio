package com.jobseeckerstudio.user.user.encryption;

import com.jobseeckerstudio.user.global.exception.EncryptionException;

import javax.crypto.Cipher;
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
