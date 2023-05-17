package quiz.global.exception;

public class InvalidParameterFromDtoException extends RuntimeException {

	String message;

	public InvalidParameterFromDtoException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
