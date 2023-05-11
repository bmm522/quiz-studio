package quiz.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import quiz.controller.dto.CommonResponse;
import quiz.exception.ExpiredTokenException;
import quiz.exception.InvalidTokenException;
import quiz.exception.NullUserKeyFromJwtTokenException;

@RestControllerAdvice
@Slf4j
public class ExceptionResponseHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public CommonResponse<?> handleInvalidTokenException(InvalidTokenException e) {
        return errorHandler(e, 401);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public CommonResponse<?> handleExpiredTokenException(ExpiredTokenException e) {
        return errorHandler(e, 401);
    }

    @ExceptionHandler(NullUserKeyFromJwtTokenException.class)
    public CommonResponse<?> handleNullUserKeyFromJwtTokenException(NullUserKeyFromJwtTokenException e) {
        return errorHandler(e, 401);
    }

    private CommonResponse<?> errorHandler(Exception e, Integer status) {
        log.error(e.getMessage());
        return CommonResponse.builder()
            .status(status)
            .msg(e.getMessage())
            .errorName(e.getClass().getSimpleName())
            .build();
    }
}
