package quiz.domain.quiz.repository;

import java.util.List;
import quiz.global.dto.CustomQuizDto;

public interface QuizCUDRepository {


	int updateAllTitleByCustomQuizDto(List<CustomQuizDto> quizzes);
}
