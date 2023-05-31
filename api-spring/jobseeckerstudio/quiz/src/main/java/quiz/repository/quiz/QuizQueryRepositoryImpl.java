package quiz.repository.quiz;

import com.querydsl.core.types.dsl.Expressions;
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

//	public List<QuizQueryDto> findQuizzesForRedisBy() {
//		List<Quiz> quizList = queryFactory
//			.selectFrom(quiz)
//			.where(quiz.category.categoryTitle.eq("java")
//				.or(quiz.category.categoryTitle.eq("data_structure")))
//			// .orderBy(Expressions.numberTemplate(Double.class, "function('RAND')").asc())
//			.fetch();
//
//		return QuizMapper.toQuizQueryDtoListForRedis(quizList);
//	}

	@Override
	public List<QuizQueryDto> findQuizQueryDtoByCategoryId(final Long categoryId) {
		List<Quiz> quizList = queryFactory
			.selectFrom(quiz)
			.where(quiz.category.id.eq(categoryId))
			.fetch();
		return QuizMapper.toQuizQueryDtoList(quizList);
	}


	@Override
	public List<QuizQueryDto> findQuizQueryDtoListByIdAndUserKey(final String userKey,
		final Long categoryId, final int offset, final int pageSize) {

		final List<Quiz> quizList = queryFactory
			.selectFrom(quiz)
			.where(quiz.category.id.eq(categoryId)
				.and(quiz.category.userKey.eq(userKey)))
			.offset(offset)
			.limit(pageSize)
			.fetch();

		return QuizMapper.toQuizQueryDtoList(quizList);
	}

	@Override
	public List<QuizQueryDto> findQuizQueryDtoListByCategoryIdAndUserKeyWithOutPaging(
		String userKey,
		Long categoryId) {
		final List<Quiz> quizList = queryFactory
			.selectFrom(quiz)
			.where(quiz.category.id.eq(categoryId)
				.and(quiz.category.userKey.eq(userKey)))
			.orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
			.limit(10)
			.fetch();
		return QuizMapper.toQuizQueryDtoList(quizList);
	}

	@Override
	public Long getQuizTotalCount(final String userKey,
		final Long categoryId) {
		return queryFactory
			.select(quiz.count())
			.from(quiz)
			.where(quiz.category.id.eq(categoryId)
				.and(quiz.category.userKey.eq(userKey)))
			.fetchOne();
	}

	@Override
	public List<Quiz> findQuizzesByCategoryId(Long categoryId) {
		return queryFactory
			.selectFrom(quiz)
			.where(quiz.category.id.eq(categoryId))
			.fetch();
	}

	@Override
	public Optional<Quiz> findQuizByQuizId(Long quizId) {
		return Optional.ofNullable(
			queryFactory
				.selectFrom(quiz)
				.where(quiz.id.eq(quizId))
				.fetchOne()
		);
	}

}
