package com.quizbatch.tasklets;

import com.quizbatch.domain.Quiz;
import com.quizbatch.domain.QuizMapper;
import java.util.List;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class MapperTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
		throws Exception {
		final ExecutionContext jobExecutionContext = chunkContext.getStepContext()
			.getStepExecution().getJobExecution().getExecutionContext();
		String response = jobExecutionContext.getString("response");
		List<Quiz> quizzes = QuizMapper.toQuizzes(response);
		System.out.println(quizzes.toString());
		System.out.println(quizzes.size());
		return RepeatStatus.FINISHED;
	}
}
