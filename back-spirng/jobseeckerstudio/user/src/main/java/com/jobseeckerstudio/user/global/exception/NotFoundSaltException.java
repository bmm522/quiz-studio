package com.jobseeckerstudio.user.global.exception;

public class NotFoundSaltException extends RuntimeException{

    String message;

    public  NotFoundSaltException(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
