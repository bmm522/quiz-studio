package com.quizbatch.config;


import com.quizbatch.config.util.MakeQuizJobFlow;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiNetworkRequestTasklet;
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
public class MakeNetworkQuizConfig extends MakeQuizJobFlow {


	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	private final ApiNetworkRequestTasklet apiNetworkRequestTasklet;

	private final Step converterFromResponseStep;
	private final Step categoryMapperStep;

	/**
	 * Network Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Network Quiz 생성 Job
	 */
	@Bean(name = "makeNetworkQuizJob")
	public Job makeNetworkQuizJob() {
		return makeQuizFlow(jobBuilderFactory, apiNetworkRequestStep(),
			converterFromResponseStep,
			categoryMapperStep, CategoryTitle.NETWORK);
	}

	@Bean(name = "apiNetworkRequestStep")
	public Step apiNetworkRequestStep() {
		return stepBuilderFactory.get("apiNetworkRequestStep")
			.tasklet(apiNetworkRequestTasklet)
			.build();
	}
}
