package quiz.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResponse<T> {

	private Integer status;
	private String msg;
	private T data;
	private String errorName;

	// 정상 요청
	@Builder
	public CommonResponse(final Integer status, final String msg, final T data,
		final String errorName) {
		this.status = status;
		this.msg = msg;
		this.data = data;
		this.errorName = errorName;
	}


	// 비 정상 요청
	@Builder
	public CommonResponse(final Integer status, final String msg, final String errorName) {
		this.status = status;
		this.msg = msg;
		this.errorName = errorName;
	}

}

