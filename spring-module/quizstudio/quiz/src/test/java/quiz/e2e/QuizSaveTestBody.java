package quiz.e2e;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import quiz.global.dto.CustomQuizDto;

@AllArgsConstructor
@Builder
@Getter
public class QuizSaveTestBody {

	private List<CustomQuizDto> quizzes;

}
