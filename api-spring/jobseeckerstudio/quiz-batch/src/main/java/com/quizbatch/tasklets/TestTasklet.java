package com.quizbatch.tasklets;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class TestTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
		throws Exception {

		StepContext stepContext = chunkContext.getStepContext();
		String test = "테스트";
		final ExecutionContext jobExecutionContext = stepContext.getStepExecution()
			.getJobExecution().getExecutionContext();

		jobExecutionContext.put("test", test);
		return RepeatStatus.FINISHED;
	}
}
