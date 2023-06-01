package quiz.controller.quiz.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import quiz.global.dto.CustomQuizDto;

@NoArgsConstructor
@Getter
public class QuizSaveBody {

	private List<CustomQuizDto> quizzes;

	@Builder
	public QuizSaveBody(List<CustomQuizDto> quizzes) {
		this.quizzes = quizzes;
	}


}
