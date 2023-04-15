package com.jobseeckerstudio.user.user.encryption;

import com.jobseeckerstudio.user.global.exception.CryptoException;
import com.jobseeckerstudio.user.user.encryption.properties.EncryptionProperties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {


    protected static final String ALGORITHM = "AES";
    protected static final String KEY = EncryptionProperties.KEY; // 대칭키

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
