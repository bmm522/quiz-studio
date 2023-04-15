package com.jobseeckerstudio.user.exception;

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
