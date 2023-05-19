package quiz.controller.dto;

public class ResponseHandler {

	public static CommonResponse<?> handle(final Integer status, final String msg,
		final Object data) {
		return CommonResponse.builder().status(status).msg(msg).data(data).build();
	}
}
