package quiz.controller.quiz.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import quiz.global.dto.CustomQuizDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class C_QuizSaveRequest {

	private List<CustomQuizDto> quizzes;
}
