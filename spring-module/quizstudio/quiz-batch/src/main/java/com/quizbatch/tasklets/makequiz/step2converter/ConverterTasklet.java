package com.quizbatch.tasklets.makequiz.step2converter;

import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import java.util.List;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;


@Component
public class ConverterTasklet implements Tasklet {

	/**
	 * API 응답을 변환하여 퀴즈 DTO 리스트로 변환하고 큐에 추가하는 메서드입니다.
	 */
	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
		throws Exception {
		final ExecutionContext jobExecutionContext = chunkContext.getStepContext()
			.getStepExecution().getJobExecution().getExecutionContext();
		String response = jobExecutionContext.getString("response");
		CategoryTitle categoryTitle = (CategoryTitle) jobExecutionContext.get("categoryTitle");
		List<QuizDtoFromResponse> quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(
			response, categoryTitle);
		QuizDtoFromResponseQueue.add(quizDtoFromResponses);
		return RepeatStatus.FINISHED;
	}
}
