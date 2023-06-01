package quiz.controller.quiz.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import quiz.global.dto.CustomQuizDto;

@Getter
@NoArgsConstructor
public class QuizUpdateBody {

	private List<CustomQuizDto> quizzes;

	@Builder
	public QuizUpdateBody(List<CustomQuizDto> quizzes) {
		this.quizzes = quizzes;
	}
}
