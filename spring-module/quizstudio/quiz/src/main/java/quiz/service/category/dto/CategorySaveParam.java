package quiz.service.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CategorySaveParam {

	private Request request;

	private Response response;


	@AllArgsConstructor
	@Builder
	@Getter
	public static class Request {

		private String userKey;

		private String title;

		private String description;
	}

	@AllArgsConstructor
	@Builder
	@Getter
	public static class Response {

		private String userKey;

		private String title;

		private String description;
	}
}
