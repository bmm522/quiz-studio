package com.quizbatch.domain.quiz;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import java.util.List;
import javax.persistence.EntityManager;


public class QuizQueryRepositoryImpl implements QuizQueryRepository {

	private final JPAQueryFactory queryFactory;

	QQuiz quiz;

	public QuizQueryRepositoryImpl(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);

		quiz = QQuiz.quiz;
	}

	public List<QuizQueryDto> getQuizzesForRedisBy() {
		List<Quiz> quizzes = queryFactory.selectFrom(quiz)
			.where(quiz.category.categoryTitle.eq(CategoryTitle.JAVA.get())
				.or(quiz.category.categoryTitle.eq(CategoryTitle.DATA_STRUCTURE.get()))
				.or(quiz.category.categoryTitle.eq(CategoryTitle.DATABASE.get())))
			.fetch();
		return QuizMapper.toQuizQueryDtoListForRedis(quizzes);
	}


}
