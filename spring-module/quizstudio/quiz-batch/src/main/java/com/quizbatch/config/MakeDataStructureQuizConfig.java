package com.quizbatch.config;

import com.quizbatch.config.util.MakeQuizJobFlow;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiDataStructureRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@RequiredArgsConstructor
@DependsOn(value = {"converterFromResponseStep", "categoryMapperStep"})
public class MakeDataStructureQuizConfig extends MakeQuizJobFlow {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	private final ApiDataStructureRequestTasklet apiDataStructureRequestTasklet;

	private final Step converterFromResponseStep;
	private final Step categoryMapperStep;

	/**
	 * Data Structure Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Data Structure Quiz 생성 Job
	 */
	@Bean(name = "makeDataStructureQuizJob")
	public Job makeDataStructureQuizJob() {
		return makeQuizFlow(jobBuilderFactory, apiDataStructureRequestStep(),
			converterFromResponseStep,
			categoryMapperStep, CategoryTitle.DATA_STRUCTURE);
	}

	@Bean(name = "apiDataStructureRequestStep")
	public Step apiDataStructureRequestStep() {
		return stepBuilderFactory.get("apiDataStructureRequestStep")
			.tasklet(apiDataStructureRequestTasklet)
			.build();
	}
}
