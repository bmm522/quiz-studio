package quiz.global.exception;

public class DuplicateTitleException extends RuntimeException {

	String message;

	public DuplicateTitleException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
