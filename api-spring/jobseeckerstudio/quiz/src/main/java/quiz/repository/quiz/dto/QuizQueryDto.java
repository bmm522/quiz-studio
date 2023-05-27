package quiz.repository.quiz.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizQueryDto {

	private String categoryTitle;

	private Long quizId;
	private String difficulty;
	private String quizTitle;

	private List<ChoiceDto> choices;

	public static QuizQueryDto createForTest(final String categoryTitle, final String quizTitle,
		final String choiceContent1, final String choiceContent2, final String choiceContent3,
		final String choiceContent4,
		final int answerIndex) {
		return QuizQueryDto.builder()
			.categoryTitle(categoryTitle)
			.quizTitle(quizTitle)
			.choiceDtos(List.of(
				ChoiceDto.builder()
					.choiceContent(choiceContent1)
					.isAnswer(answerIndex == 1)
					.build(),
				ChoiceDto.builder()
					.choiceContent(choiceContent2)
					.isAnswer(answerIndex == 2)
					.build(),
				ChoiceDto.builder()
					.choiceContent(choiceContent3)
					.isAnswer(answerIndex == 3)
					.build(),
				ChoiceDto.builder()
					.choiceContent(choiceContent4)
					.isAnswer(answerIndex == 4)
					.build()
			))
			.build();
	}

	@Builder
	public QuizQueryDto(final Long quizId, final String categoryTitle, final String difficulty,
		final String quizTitle, final List<ChoiceDto> choiceDtos) {
		this.quizId = quizId;
		this.categoryTitle = categoryTitle;
		this.difficulty = difficulty;
		this.quizTitle = quizTitle;
		this.choices = choiceDtos;
	}

	@Getter
	public static class ChoiceDto {

		private Long choiceId;
		private String choiceContent;
		private boolean isAnswer;

		@Builder
		public ChoiceDto(final String choiceContent, final boolean isAnswer) {
			this.choiceContent = choiceContent;
			this.isAnswer = isAnswer;
		}

		@Builder
		public ChoiceDto(final Long choiceId, final String choiceContent, final boolean isAnswer) {
			this.choiceId = choiceId;
			this.choiceContent = choiceContent;
			this.isAnswer = isAnswer;
		}
	}
}
