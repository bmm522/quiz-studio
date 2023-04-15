package com.jobseeckerstudio.user.global.exception;

public class ExpiredTokenException extends RuntimeException {

    String message;

    public  ExpiredTokenException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
