package quiz.global.exception;

public class ExistCategorySaveException extends RuntimeException {

	String message;

	public ExistCategorySaveException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
