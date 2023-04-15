package com.jobseeckerstudio.user.global.exception;

public class CryptoException extends RuntimeException{

    String message;

    public CryptoException (String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
