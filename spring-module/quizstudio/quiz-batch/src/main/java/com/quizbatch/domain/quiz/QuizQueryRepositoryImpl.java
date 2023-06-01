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

	/**
	 * Redis에 저장하기 위한 퀴즈 목록을 조회하는 메서드입니다. Java, Data Structure, Database 카테고리에 속하는 퀴즈를 조회합니다.
	 *
	 * @return 퀴즈의 쿼리 DTO 목록
	 */
	public List<QuizQueryDto> getQuizzesForRedisBy() {
		List<Quiz> quizzes = queryFactory.selectFrom(quiz)
			.where(quiz.category.categoryTitle.eq(CategoryTitle.JAVA.get())
				.or(quiz.category.categoryTitle.eq(CategoryTitle.DATA_STRUCTURE.get()))
				.or(quiz.category.categoryTitle.eq(CategoryTitle.DATABASE.get())))
			.fetch();
		return QuizMapper.toQuizQueryDtoListForRedis(quizzes);
	}


}
