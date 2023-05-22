package quiz.repository.quiz;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import quiz.domain.quiz.QQuiz;
import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.mapper.QuizMapper;
import quiz.repository.quiz.dto.QuizQueryDto;


public class QuizQueryRepositoryImpl implements QuizQueryRepository {

	private final JPAQueryFactory queryFactory;

	QQuiz quiz;

	public QuizQueryRepositoryImpl(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);

		quiz = QQuiz.quiz;
	}

	public List<QuizQueryDto> findQuizzes() {
		List<Quiz> quizList = queryFactory
			.selectFrom(quiz)
			.where(quiz.category.categoryTitle.eq("java")
				.or(quiz.category.categoryTitle.eq("data_structure")))
			// .orderBy(Expressions.numberTemplate(Double.class, "function('RAND')").asc())
			.fetch();

		return QuizMapper.toQuizQueryDtoListForRedis(quizList);
	}

	@Override
	public List<QuizQueryDto> findQuizQueryDtoByCategoryId(final Long categoryId) {
		List<Quiz> quizList = queryFactory
			.selectFrom(quiz)
			.where(quiz.category.categoryId.eq(categoryId))
			.fetch();
		return QuizMapper.toQuizQueryDtoList(quizList);
	}


	@Override
	public List<QuizQueryDto> findQuizQueryDtoListByCategoryIdAndUserKey(final String userKey,
		final Long categoryId) {
		List<Quiz> quizList = queryFactory
			.selectFrom(quiz)
			.where(quiz.category.categoryId.eq(categoryId)
				.and(quiz.category.userKey.eq(userKey)))
			.fetch();
		return QuizMapper.toQuizQueryDtoList(quizList);
	}

	@Override
	public List<Quiz> findQuizzesByCategoryId(Long categoryId) {
		return queryFactory
			.selectFrom(quiz)
			.where(quiz.category.categoryId.eq(categoryId))
			.fetch();
	}

	@Override
	public Optional<Quiz> findQuizByQuizId(Long quizId) {
		return Optional.ofNullable(
			queryFactory
				.selectFrom(quiz)
				.where(quiz.quizId.eq(quizId))
				.fetchOne()
		);
	}

}
