package com.jobseeckerstudio.user.exception.handler;

import com.jobseeckerstudio.user.exception.*;
import com.jobseeckerstudio.user.controller.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionResponseHandler {


    @ExceptionHandler(LoginFailException.class)
    public CommonResponse<?> handleLoginFailException(LoginFailException e){
        return errorHandler(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundSocialInfoException.class)
    public CommonResponse<?> handleNotFoundSocialInfoException(NotFoundSocialInfoException e) {
        return errorHandler(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public CommonResponse<?> handleUnauthorizedException(UnauthorizedException e) {
        return errorHandler(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public CommonResponse<?> handleExpiredTokenException(ExpiredTokenException e) {
        return errorHandler(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundSaltException.class)
    public CommonResponse<?> handleNotFoundHandler(NotFoundSaltException e) {
        return errorHandler(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundUserException.class)
    public CommonResponse<?> handleNotFoundUserException(NotFoundUserException e) {
        return errorHandler(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EncryptionException.class)
    public CommonResponse<?> handleEncryptionException(EncryptionException e) {
        return errorHandler(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DecryptionException.class)
    public CommonResponse<?> handleDecryptionException(DecryptionException e) {
        return errorHandler(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CryptoException.class)
    public CommonResponse<?> handleCryptoException(CryptoException e) {
        return errorHandler(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }





    private CommonResponse<?> errorHandler(Exception e, HttpStatus status) {
        log.error(e.getMessage());
        return CommonResponse.builder()
            .status(status)
            .msg(e.getMessage())
            .errorName(e.getClass().getName())
            .build();
    }

}
