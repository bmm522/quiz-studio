package quiz.domain.quiz.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import quiz.domain.quiz.QQuiz;
import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.repository.dto.QuizDto;
import quiz.domain.quiz.repository.mapper.QuizMapper;


public class QuizMySqlQueryRepositoryImpl implements QuizMySqlQueryRepository {

	private final JPAQueryFactory queryFactory;

	QQuiz quiz;

	public QuizMySqlQueryRepositoryImpl(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);

		quiz = QQuiz.quiz;
	}

	public List<QuizDto> findQuizzes() {
		List<Quiz> quizList = queryFactory
			.selectFrom(quiz)
			.where(quiz.category.categoryName.eq("java")
				.or(quiz.category.categoryName.eq("data_structure")))
			// .orderBy(Expressions.numberTemplate(Double.class, "function('RAND')").asc())
			.fetch();

		return QuizMapper.toQuizDtoList(quizList);
	}

	@Override
	public List<QuizDto> findQuizDtoByCategoryId(final long categoryId) {
		List<Quiz> quizList = queryFactory
			.selectFrom(quiz)
			.where(quiz.category.categoryId.eq(categoryId))
			.fetch();
		return QuizMapper.toQuizDtoList(quizList);
	}
}
