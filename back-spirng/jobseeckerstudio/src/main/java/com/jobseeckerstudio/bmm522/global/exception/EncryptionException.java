package com.jobseeckerstudio.bmm522.global.exception;

public class EncryptionException extends RuntimeException {

    String message;

    public EncryptionException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
