package com.jobseeckerstudio.user.exception;

public class LoginFailException extends RuntimeException{

    String message;

    public  LoginFailException(String message){
        this.message = message;
    }
    public  LoginFailException(String message, Throwable cause){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
