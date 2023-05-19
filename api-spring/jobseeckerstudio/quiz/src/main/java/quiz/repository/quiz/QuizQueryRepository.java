package quiz.repository.quiz;

import java.util.List;
import quiz.repository.quiz.dto.QuizQueryDto;

public interface QuizQueryRepository {

	List<QuizQueryDto> findQuizzes();

	List<QuizQueryDto> findQuizQueryDtoByCategoryId(final Long categoryId);

	List<QuizQueryDto> findQuizQueryDtoListByCategoryIdAndUserKey(final String userKey,
		final Long categoryId);
}
