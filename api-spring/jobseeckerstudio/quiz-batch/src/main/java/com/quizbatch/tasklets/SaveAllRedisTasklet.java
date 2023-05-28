package com.quizbatch.tasklets;

import com.quizbatch.domain.Quiz;
import com.quizbatch.domain.QuizRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveAllRedisTasklet implements Tasklet {


	private final QuizRepository quizRepository;

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
		throws Exception {
		final ExecutionContext jobExecutionContext = chunkContext.getStepContext()
			.getStepExecution().getJobExecution().getExecutionContext();
		List<Quiz> quizzes = (List<Quiz>) jobExecutionContext.get("quizzes");
		quizRepository.deleteAll();
		quizRepository.saveAll(quizzes);
		return RepeatStatus.FINISHED;
	}
}
