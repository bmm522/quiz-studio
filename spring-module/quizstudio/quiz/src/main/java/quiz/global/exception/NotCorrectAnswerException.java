package quiz.global.exception;

public class NotCorrectAnswerException extends RuntimeException {

	String message;

	public NotCorrectAnswerException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
