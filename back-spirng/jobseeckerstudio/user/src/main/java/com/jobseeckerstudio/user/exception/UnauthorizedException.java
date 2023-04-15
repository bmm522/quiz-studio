package com.jobseeckerstudio.user.exception;

public class UnauthorizedException extends RuntimeException{

    String message;

    public  UnauthorizedException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
