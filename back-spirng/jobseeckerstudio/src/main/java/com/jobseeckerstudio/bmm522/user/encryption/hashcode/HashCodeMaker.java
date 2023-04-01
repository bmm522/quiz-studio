package com.jobseeckerstudio.bmm522.user.encryption.hashcode;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashCodeMaker {
    public String getHashCode(String salt, String email) {
        return actionOfMakeHash(plusStr(salt, email));
    }


    public String actionOfMakeHash(String plusStr) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(plusStr.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return String.format("%064x", new BigInteger(1, md.digest()));
    }

    public String plusStr(String salt, String email) {
        return salt+email;
    }

}
