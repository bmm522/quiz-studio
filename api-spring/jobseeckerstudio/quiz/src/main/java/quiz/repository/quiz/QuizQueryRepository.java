package quiz.repository.quiz;

import java.util.List;
import java.util.Optional;
import quiz.domain.quiz.Quiz;
import quiz.repository.quiz.dto.QuizQueryDto;

public interface QuizQueryRepository {

	List<QuizQueryDto> findQuizzesForRedisBy();

	List<QuizQueryDto> findQuizQueryDtoByCategoryId(final Long categoryId);


	List<QuizQueryDto> findQuizQueryDtoListByIdAndUserKey(final String userKey,
		final Long categoryId, final int offset, final int pageSize);

	List<QuizQueryDto> findQuizQueryDtoListByCategoryIdAndUserKeyWithOutPaging(String userKey,
		final Long categoryId);

	Long getQuizTotalCount(final String userKey,
		final Long categoryId);

	List<Quiz> findQuizzesByCategoryId(final Long categoryId);

	Optional<Quiz> findQuizByQuizId(final Long quizId);


}
