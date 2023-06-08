package com.quizbatch.tasklets.saveredis.step1clearAll;

import com.quizbatch.domain.quiz.QuizRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClearQuizAtRedisTasklet implements Tasklet {

	private final QuizRedisRepository quizRedisRepository;

	/**
	 * 기존의 Redis안에 있는 퀴즈를 삭제하는 Tasklet입니다.
	 *
	 * @param stepContribution StepContribution 객체
	 * @param chunkContext     ChunkContext 객체
	 * @return RepeatStatus.FINISHED
	 * @throws Exception 예외 발생 시
	 */
	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
		throws Exception {
		quizRedisRepository.deleteAll();
		return RepeatStatus.FINISHED;
	}
}
