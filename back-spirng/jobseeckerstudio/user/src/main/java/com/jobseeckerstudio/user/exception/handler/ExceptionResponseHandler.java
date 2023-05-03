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
        return errorHandler(e, 401);
    }

    @ExceptionHandler(NotFoundSocialInfoException.class)
    public CommonResponse<?> handleNotFoundSocialInfoException(NotFoundSocialInfoException e) {
        return errorHandler(e, 400);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public CommonResponse<?> handleUnauthorizedException(UnauthorizedException e) {
        return errorHandler(e, 401);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public CommonResponse<?> handleExpiredTokenException(ExpiredTokenException e) {
        return errorHandler(e, 401);
    }

    @ExceptionHandler(NotFoundSaltException.class)
    public CommonResponse<?> handleNotFoundHandler(NotFoundSaltException e) {
        return errorHandler(e, 400);
    }

    @ExceptionHandler(NotFoundUserException.class)
    public CommonResponse<?> handleNotFoundUserException(NotFoundUserException e) {
        return errorHandler(e, 500);
    }

    @ExceptionHandler(EncryptionException.class)
    public CommonResponse<?> handleEncryptionException(EncryptionException e) {
        return errorHandler(e, 500);
    }

    @ExceptionHandler(DecryptionException.class)
    public CommonResponse<?> handleDecryptionException(DecryptionException e) {
        return errorHandler(e, 500);
    }

    @ExceptionHandler(CryptoException.class)
    public CommonResponse<?> handleCryptoException(CryptoException e) {
        return errorHandler(e, 500);
    }





    private CommonResponse<?> errorHandler(Exception e, Integer status) {
        log.error(e.getMessage());
        return CommonResponse.builder()
            .status(status)
            .msg(e.getMessage())
            .errorName(e.getClass().getName())
            .build();
    }

}
