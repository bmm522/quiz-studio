package quiz.repository.quiz.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizQueryDto {

	private final String categoryName;
	private final String difficulty;
	private final String quizTitle;
	private final List<ChoiceDto> choiceDtos;


	@Builder
	public QuizQueryDto(String categoryName, String difficulty, String quizTitle,
		List<ChoiceDto> choiceDtos) {
		this.categoryName = categoryName;
		this.difficulty = difficulty;
		this.quizTitle = quizTitle;
		this.choiceDtos = choiceDtos;
	}

	@Getter
	public static class ChoiceDto {

		private final String choiceContent;
		private final boolean isAnswer;

		@Builder
		public ChoiceDto(String choiceContent, boolean isAnswer) {
			this.choiceContent = choiceContent;
			this.isAnswer = isAnswer;
		}
	}
}
