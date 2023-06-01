package com.jobseeckerstudio.user.exception;

public class DecryptionException extends RuntimeException{

    String message;

    public DecryptionException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
