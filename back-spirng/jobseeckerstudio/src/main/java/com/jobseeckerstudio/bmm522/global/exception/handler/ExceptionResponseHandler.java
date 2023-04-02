package com.jobseeckerstudio.bmm522.global.exception.handler;

import com.jobseeckerstudio.bmm522.global.exception.ExpiredTokenException;
import com.jobseeckerstudio.bmm522.global.exception.LoginFailException;
import com.jobseeckerstudio.bmm522.global.exception.NotFoundSocialInfoException;
import com.jobseeckerstudio.bmm522.global.exception.UnauthorizedException;
import com.jobseeckerstudio.bmm522.user.controller.dto.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResponseHandler {


    @ExceptionHandler(LoginFailException.class)
    public CommonResponse<?> handleLoginFailException(LoginFailException e){
        return CommonResponse.builder()
            .status(HttpStatus.UNAUTHORIZED)
            .msg(e.getMessage())
            .errorName(e.getClass().getName())
            .build();
    }

    @ExceptionHandler(NotFoundSocialInfoException.class)
    public CommonResponse<?> handleNotFoundSocialInfoException(NotFoundSocialInfoException e) {
        return CommonResponse.builder()
            .status(HttpStatus.BAD_REQUEST)
            .msg(e.getMessage())
            .errorName(e.getClass().getName())
            .build();
    }

    @ExceptionHandler(UnauthorizedException.class)
    public CommonResponse<?> handleUnauthorizedException(UnauthorizedException e) {
        return CommonResponse.builder()
            .status(HttpStatus.UNAUTHORIZED)
            .msg(e.getMessage())
            .errorName(e.getClass().getName())
            .build();
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public CommonResponse<?> handleExpiredTokenException(ExpiredTokenException e) {
        return CommonResponse.builder()
            .status(HttpStatus.UNAUTHORIZED)
            .msg(e.getMessage())
            .errorName(e.getClass().getName())
            .build();
    }

}
