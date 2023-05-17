package quiz.service.quiz.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import quiz.global.dto.CustomQuizDto;

@AllArgsConstructor
@Builder
@Getter
public class S_QuizSaveRequest {

	private String userKey;

	private List<CustomQuizDto> quizzes;

	private long userCategoryId;

	public List<CustomQuizDto.Choice> getQuizzesChoice(int i) {
		return this.quizzes.get(i).getChoices();
	}

}
