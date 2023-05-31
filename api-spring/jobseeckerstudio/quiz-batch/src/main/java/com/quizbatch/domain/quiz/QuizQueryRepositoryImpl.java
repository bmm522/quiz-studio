package com.quizbatch.domain.quiz;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuizQueryRepositoryImpl implements QuizQueryRepository {

	private final EntityManager entityManager;

	public List<QuizQueryDto> getQuizzesForRedisBy() {
		List<Quiz> quizzes = entityManager.createQuery(
				"SELECT q FROM Quiz q JOIN q.category c WHERE c.categoryTitle = 'java' OR c.categoryTitle = 'data_structure'",
				Quiz.class)
			.getResultList();

		return QuizMapper.toQuizQueryDtoListForRedis(quizzes);
	}


}
