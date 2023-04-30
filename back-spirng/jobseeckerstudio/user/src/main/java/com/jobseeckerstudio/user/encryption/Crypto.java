package com.jobseeckerstudio.user.encryption;

import com.jobseeckerstudio.user.exception.CryptoException;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {


    protected static final String ALGORITHM = "AES";

    @Value("${encryption.secret}")
    protected static String KEY;

    protected static Cipher getCipher(String algorithm,int mode) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(mode, secretKeySpec);
            return cipher;
        } catch (Exception e) {
            throw new CryptoException(e.getMessage());
        }
    }


}
