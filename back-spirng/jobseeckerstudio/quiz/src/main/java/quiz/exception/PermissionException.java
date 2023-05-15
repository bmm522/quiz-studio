package quiz.exception;

public class PermissionException extends RuntimeException{

    String message;
    public PermissionException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
