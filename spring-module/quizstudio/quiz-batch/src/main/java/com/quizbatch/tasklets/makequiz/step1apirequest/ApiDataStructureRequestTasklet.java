package com.quizbatch.tasklets.makequiz.step1apirequest;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ApiDataStructureRequestTasklet implements Tasklet {

	@Qualifier("openaiRestTemplate")
	private final RestTemplate restTemplate;

	@Value("${openai.model}")
	private String model;

	@Value("${openai.api.url}")
	private String apiUrl;

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
		throws Exception {
		StepContext stepContext = chunkContext.getStepContext();
		final ExecutionContext jobExecutionContext = stepContext.getStepExecution()
			.getJobExecution().getExecutionContext();

		ChatRequest request = new ChatRequest(model, CategoryTitle.DATA_STRUCTURE);
		ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
		String responseJson = response.getChoices().get(0).getMessage().getContent();
		jobExecutionContext.put("response", responseJson);
		jobExecutionContext.put("categoryTitle", CategoryTitle.DATA_STRUCTURE);
		return RepeatStatus.FINISHED;
	}


}
