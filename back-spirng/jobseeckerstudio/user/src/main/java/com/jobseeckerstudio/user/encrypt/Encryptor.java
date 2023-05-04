package com.jobseeckerstudio.user.encrypt;

import com.jobseeckerstudio.user.exception.EncryptionException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class Encryptor extends Crypto{

    public static String encrypt(String str)  {
        try {

            Cipher cipher = getCipher(ALGORITHM, Cipher.ENCRYPT_MODE);
            byte[] encryptedBytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch(Exception e) {
//            log.error(e.getMessage());
            throw  new EncryptionException(e.getMessage());
        }

    }

}
