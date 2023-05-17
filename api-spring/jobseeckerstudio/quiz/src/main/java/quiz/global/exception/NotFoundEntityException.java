package quiz.global.exception;

public class NotFoundEntityException extends RuntimeException {

	String message;

	public NotFoundEntityException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
