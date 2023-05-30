package com.quizbatch.domain.quiz;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
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

	public List<QuizQueryDto> findQuizzesFroRedis() {
		List<Quiz> quizList = queryFactory
			.selectFrom(quiz)
			.where(quiz.category.categoryTitle.eq("java")
				.or(quiz.category.categoryTitle.eq("data_structure")))
			// .orderBy(Expressions.numberTemplate(Double.class, "function('RAND')").asc())
			.fetch();

		return QuizMapper.toQuizQueryDtoListForRedis(quizList);
	}
}
