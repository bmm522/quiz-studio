package quiz.domain.quiz.repository;

import java.util.List;
import quiz.domain.quiz.repository.dto.QuizQueryDto;

public interface QuizMySqlQueryRepository {

	List<QuizQueryDto> findQuizzes();

	List<QuizQueryDto> findQuizDtoByCategoryId(long categoryId);

	List<QuizQueryDto> findQuizDtoByCategoryIdAndUserKey(String userKey, long categoryId);
}
