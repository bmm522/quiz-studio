package com.quizbatch.domain.quiz;

import java.util.List;
import quiz.repository.quiz.dto.QuizQueryDto;

public interface QuizQueryRepository {

	List<QuizQueryDto> getQuizzesForRedisBy();

}
