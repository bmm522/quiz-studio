package com.jobseeckerstudio.bmm522.global.exception;

public class NotFoundSocialInfoException extends RuntimeException{

    String message;
    public  NotFoundSocialInfoException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
