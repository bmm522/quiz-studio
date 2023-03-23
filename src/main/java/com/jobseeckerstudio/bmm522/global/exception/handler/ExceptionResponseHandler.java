package com.jobseeckerstudio.bmm522.global.exception.handler;

import com.jobseeckerstudio.bmm522.global.exception.LoginFailException;
import com.jobseeckerstudio.bmm522.global.exception.NotFoundSocialInfoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResponseHandler {


    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity<?> handleLoginFailException(LoginFailException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundSocialInfoException.class)
    public ResponseEntity<?> handleNotFoundSocialInfoException(NotFoundSocialInfoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
