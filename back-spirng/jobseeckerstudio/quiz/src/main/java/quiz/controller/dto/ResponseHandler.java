package quiz.controller.dto;

public class ResponseHandler {

    public static CommonResponse<?> handle(Integer status, String msg, Object data) {
        return CommonResponse.builder().status(status).msg(msg).data(data).build();
    }
}
