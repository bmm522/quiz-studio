package com.jobseeckerstudio.bmm522.user.encryption.hashcode;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCodeMaker {
    public String actionOfMakeHash(String salt, String email) {
        return actionOfEncryption(plusStr(salt, email));
    }


    public String actionOfEncryption(String plusStr) {
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
