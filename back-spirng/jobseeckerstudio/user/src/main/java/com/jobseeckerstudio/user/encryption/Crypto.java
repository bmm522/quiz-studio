package com.jobseeckerstudio.user.encryption;

import com.jobseeckerstudio.user.encryption.properties.EncryptionProperties;
import com.jobseeckerstudio.user.exception.CryptoException;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {


    protected static final String ALGORITHM = "AES";

    protected static Cipher getCipher(String algorithm,int mode) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(EncryptionProperties.KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(mode, secretKeySpec);
            return cipher;
        } catch (Exception e) {
            throw new CryptoException(e.getMessage());
        }
    }


}
