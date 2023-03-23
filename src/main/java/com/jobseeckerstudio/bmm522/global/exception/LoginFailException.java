package com.jobseeckerstudio.bmm522.global.exception;

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
