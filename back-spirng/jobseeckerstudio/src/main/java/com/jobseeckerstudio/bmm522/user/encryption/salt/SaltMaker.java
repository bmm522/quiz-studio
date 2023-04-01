package com.jobseeckerstudio.bmm522.user.encryption.salt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SaltMaker {

        public String actionOfMakeSalt() {
            byte[] randomByte = makeRandomByteArray();
            return new String(Base64.getEncoder().encode(randomByte));
        }

        private byte[] makeRandomByteArray() {
            byte[] randomByte = null;
            try {
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                randomByte = new byte[16];
                random.nextBytes(randomByte);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return randomByte;
        }

        public String getSalt() {
            return actionOfMakeSalt();
        }


}
