package quiz.exception;

public class NullUserKeyFromJwtTokenException extends RuntimeException{

    String message;
    public NullUserKeyFromJwtTokenException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
