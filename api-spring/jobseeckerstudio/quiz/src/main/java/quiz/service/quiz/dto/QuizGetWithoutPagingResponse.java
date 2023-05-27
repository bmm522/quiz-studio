package quiz.service.quiz.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import quiz.repository.quiz.dto.QuizQueryDto;


@AllArgsConstructor
@Builder
@Getter
public class QuizGetWithoutPagingResponse {

	private List<QuizQueryDto> quizzes;

}
