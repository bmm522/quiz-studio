package com.jobseeckerstudio.user.exception;

public class NotFoundTokenFromHeaderException extends RuntimeException{

    String message;

    public NotFoundTokenFromHeaderException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
