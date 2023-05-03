package com.jobseeckerstudio.user.exception;

public class InvalidTokenException extends RuntimeException{
    String message;
    public   InvalidTokenException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
