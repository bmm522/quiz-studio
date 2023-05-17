package quiz.service.quiz.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import quiz.global.dto.CustomQuizDto;

@AllArgsConstructor
@Getter
@Builder
public class C_QuizSaveResponse {

	private List<CustomQuizDto> quizzes;

	private String userKey;

	private long categoryId;


}
