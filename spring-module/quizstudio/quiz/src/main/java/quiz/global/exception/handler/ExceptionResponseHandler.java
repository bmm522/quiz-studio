package quiz.global.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import quiz.controller.dto.CommonResponse;
import quiz.global.exception.DuplicateTitleException;
import quiz.global.exception.ExistCategorySaveException;
import quiz.global.exception.ExpiredTokenException;
import quiz.global.exception.InvalidParameterFromDtoException;
import quiz.global.exception.InvalidTokenException;
import quiz.global.exception.NotCorrectAnswerException;
import quiz.global.exception.NotFoundEntityException;
import quiz.global.exception.NullUserKeyFromJwtTokenException;
import quiz.global.exception.PermissionException;

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
	public CommonResponse<?> handleNullUserKeyFromJwtTokenException(
		NullUserKeyFromJwtTokenException e) {
		return errorHandler(e, 401);
	}

	@ExceptionHandler(InvalidParameterFromDtoException.class)
	public CommonResponse<?> handleInvalidParameterFromDtoException(
		InvalidParameterFromDtoException e) {
		return errorHandler(e, 400);
	}

	@ExceptionHandler(DuplicateTitleException.class)
	public CommonResponse<?> handleDuplicateTitleException(DuplicateTitleException e) {
		return errorHandler(e, 400);
	}

	@ExceptionHandler(ExistCategorySaveException.class)
	public CommonResponse<?> handleExistCategorySaveException(ExistCategorySaveException e) {
		return errorHandler(e, 400);
	}

	@ExceptionHandler(PermissionException.class)
	public CommonResponse<?> handlePermissionException(PermissionException e) {
		return errorHandler(e, 400);
	}

	@ExceptionHandler(NotFoundEntityException.class)
	public CommonResponse<?> handleNotFoundEntityException(NotFoundEntityException e) {
		return errorHandler(e, 500);
	}

	@ExceptionHandler(NotCorrectAnswerException.class)
	public CommonResponse<?> handleNotCorrectAnswerException(NotCorrectAnswerException e) {
		return errorHandler(e, 400);
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
