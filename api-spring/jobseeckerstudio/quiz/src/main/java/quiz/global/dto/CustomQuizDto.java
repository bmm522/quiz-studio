package quiz.global.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CustomQuizDto {

	private String title;
	private List<Choice> choices;

	// Test용 생성자
	public static CustomQuizDto createForTest(String title, String choice1, String choice2,
		String choice3, String choice4, int answerIndex) {
		List<Choice> choices = new ArrayList<>();
		choices.add(Choice.builder().content(choice1).isAnswer(answerIndex == 1).build());
		choices.add(Choice.builder().content(choice2).isAnswer(answerIndex == 2).build());
		choices.add(Choice.builder().content(choice3).isAnswer(answerIndex == 3).build());
		choices.add(Choice.builder().content(choice4).isAnswer(answerIndex == 4).build());

		return CustomQuizDto.builder()
			.title(title)
			.choices(choices)
			.build();
	}


	@AllArgsConstructor
	@Builder
	@Getter
	public static class Choice {

		private String content;
		private boolean isAnswer;

		public boolean getIsAnswer() {
			return this.isAnswer;
		}
	}
}
