package quiz.service.quiz.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import quiz.global.dto.CustomQuizDto;

@AllArgsConstructor
@Builder
@Getter
public class QuizSaveParam {

	private Request request;

	private Response response;

	@AllArgsConstructor
	@Builder
	@Getter
	public static class Request {

		private String userKey;

		private List<CustomQuizDto> quizzes;

		private Long categoryId;

		public List<CustomQuizDto.Choice> getQuizzesChoice(int i) {
			return this.quizzes.get(i).getChoices();
		}
	}

	@AllArgsConstructor
	@Builder
	@Getter
	public static class Response {

		private List<CustomQuizDto> quizzes;

		private String userKey;

		private long categoryId;
	}

}
