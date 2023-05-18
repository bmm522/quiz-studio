package quiz.repository.quiz;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import quiz.domain.quiz.QQuiz;
import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.mapper.QuizMapper;
import quiz.repository.quiz.dto.QuizQueryDto;


public class QuizMySqlQueryRepositoryImpl implements QuizMySqlQueryRepository {

	private final JPAQueryFactory queryFactory;

	QQuiz quiz;

	public QuizMySqlQueryRepositoryImpl(EntityManager entityManager) {
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

		return QuizMapper.toQuizDtoList(quizList);
	}

	@Override
	public List<QuizQueryDto> findQuizDtoByCategoryId(final long categoryId) {
		List<Quiz> quizList = queryFactory
			.selectFrom(quiz)
			.where(quiz.category.categoryId.eq(categoryId))
			.fetch();
		return QuizMapper.toQuizDtoList(quizList);
	}


	@Override
	public List<QuizQueryDto> findQuizDtoByCategoryIdAndUserKey(String userKey, long categoryId) {
//		List<Quiz> quizList = queryFactory
//			.selectFrom(quiz)
//			.where(quiz.category.categoryId.eq(categoryId)
//				.and(quiz.category.userCategory.userKey.eq(userKey)))
//			.fetch();
//		return QuizMapper.toQuizDtoList(quizList);
		return null;
	}
}
