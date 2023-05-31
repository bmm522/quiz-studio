package com.quizbatch.tasklets.saveredis.step1save;

import com.quizbatch.domain.quiz.QuizRedisRepository;
import com.quizbatch.domain.quiz.QuizRepository;
import com.quizbatch.domain.quizschema.QuizSchema;
import com.quizbatch.domain.quizschema.QuizSchemaMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import quiz.repository.quiz.dto.QuizQueryDto;

@Component
@RequiredArgsConstructor
public class SaveQuizAtRedisTasklet implements Tasklet {


	private final QuizRedisRepository quizRedisRepository;

	private final QuizRepository quizRepository;

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
		throws Exception {
		List<QuizQueryDto> quizzes = quizRepository.getQuizzesForRedisBy();
		List<QuizSchema> quizSchemas = QuizSchemaMapper.toQuizSchemas(quizzes);
		quizRedisRepository.deleteAll();
		quizRedisRepository.saveAll(quizSchemas);
		return RepeatStatus.FINISHED;
	}
}
