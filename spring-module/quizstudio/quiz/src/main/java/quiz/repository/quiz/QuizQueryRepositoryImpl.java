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


	/**
	 * 카테고리 ID에 해당하는 퀴즈의 쿼리 DTO 목록을 조회하는 메서드입니다.
	 *
	 * @param categoryId 카테고리 ID
	 * @return 퀴즈의 쿼리 DTO 목록
	 */
	@Override
	public List<QuizQueryDto> findQuizQueryDtoByCategoryId(final Long categoryId) {
		final List<Quiz> quizList = queryFactory
			.selectFrom(quiz)
			.where(quiz.category.id.eq(categoryId))
			.fetch();
		return QuizMapper.toQuizQueryDtoList(quizList);
	}

	/**
	 * 사용자 키와 카테고리 ID에 해당하는 퀴즈의 쿼리 DTO 목록을 조회하는 메서드입니다.
	 *
	 * @param userKey    사용자 키
	 * @param categoryId 카테고리 ID
	 * @param offset     조회 시작 위치
	 * @param pageSize   페이지 크기
	 * @return 퀴즈의 쿼리 DTO 목록
	 */
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

	/**
	 * 사용자 키와 카테고리 ID에 해당하는 퀴즈의 쿼리 DTO 목록을 페이징 없이 조회하는 메서드입니다.
	 *
	 * @param userKey    사용자 키
	 * @param categoryId 카테고리 ID
	 * @return 퀴즈의 쿼리 DTO 목록
	 */
	@Override
	public List<QuizQueryDto> findQuizQueryDtoListByCategoryIdAndUserKeyWithOutPaging(
		final String userKey,
		final Long categoryId) {
		final List<Quiz> quizList = queryFactory
			.selectFrom(quiz)
			.where(quiz.category.id.eq(categoryId)
				.and(quiz.category.userKey.eq(userKey)))
			.orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
			.limit(10)
			.fetch();
		return QuizMapper.toQuizQueryDtoList(quizList);
	}

	/**
	 * 사용자 키와 카테고리 ID에 해당하는 퀴즈의 총 개수를 조회하는 메서드입니다.
	 *
	 * @param userKey    사용자 키
	 * @param categoryId 카테고리 ID
	 * @return 퀴즈의 총 개수
	 */
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

	/**
	 * 카테고리 ID에 해당하는 퀴즈 목록을 조회하는 메서드입니다.
	 *
	 * @param categoryId 카테고리 ID
	 * @return 퀴즈 목록
	 */
	@Override
	public List<Quiz> findQuizzesByCategoryId(final Long categoryId) {
		return queryFactory
			.selectFrom(quiz)
			.where(quiz.category.id.eq(categoryId))
			.fetch();
	}

	/**
	 * 퀴즈 ID에 해당하는 퀴즈를 조회하는 메서드입니다.
	 *
	 * @param quizId 퀴즈 ID
	 * @return 조회된 퀴즈 객체 (존재하지 않을 경우 Optional.empty())
	 */
	@Override
	public Optional<Quiz> findQuizByQuizId(final Long quizId) {
		return Optional.ofNullable(
			queryFactory
				.selectFrom(quiz)
				.where(quiz.id.eq(quizId))
				.fetchOne()
		);
	}
}
