package quiz.service.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CategoryUpdateParam {

	private Request request;

	private Response response;

	@AllArgsConstructor
	@Builder
	@Getter
	public static class Request {

		private Long categoryId;

		private String userKey;

		private String updateTitle;

		private String updateDescription;
	}

	@AllArgsConstructor
	@Getter
	@Builder
	public static class Response {

		private String userKey;

		private String updateTitle;

		private String updateDescription;
	}

}
