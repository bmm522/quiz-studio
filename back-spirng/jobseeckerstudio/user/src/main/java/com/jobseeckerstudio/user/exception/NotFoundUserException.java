package com.jobseeckerstudio.user.exception;

public class NotFoundUserException extends RuntimeException{

    String message;

    public NotFoundUserException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
