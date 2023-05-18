package quiz.service.quiz.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import quiz.domain.quiz.repository.dto.QuizQueryDto;

@AllArgsConstructor
@Builder
@Getter
public class S_QuizGetResponse {

	private List<QuizQueryDto> quizzes;

}
