package quiz.repository.quiz;

import java.util.List;
import quiz.repository.quiz.dto.QuizQueryDto;

public interface QuizMySqlQueryRepository {

	List<QuizQueryDto> findQuizzes();

	List<QuizQueryDto> findQuizDtoByCategoryId(long categoryId);

	List<QuizQueryDto> findQuizDtoByCategoryIdAndUserKey(String userKey, long categoryId);
}
