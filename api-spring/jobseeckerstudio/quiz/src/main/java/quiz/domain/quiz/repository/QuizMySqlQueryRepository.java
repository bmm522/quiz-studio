package quiz.domain.quiz.repository;

import java.util.List;
import quiz.domain.quiz.repository.dto.QuizDto;

public interface QuizMySqlQueryRepository {

	List<QuizDto> findQuizzes();

	List<QuizDto> findQuizDtoByCategoryId(long categoryId);
}
