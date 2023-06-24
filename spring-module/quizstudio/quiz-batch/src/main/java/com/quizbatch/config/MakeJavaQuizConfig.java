package com.quizbatch.config;

import com.quizbatch.config.util.MakeQuizJobFlow;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiJavaRequestTasklet;
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
public class MakeJavaQuizConfig extends MakeQuizJobFlow {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	private final ApiJavaRequestTasklet apiJavaRequestTasklet;

	private final Step converterFromResponseStep;
	private final Step categoryMapperStep;

	/**
	 * Java Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Java Quiz 생성 Job
	 */
	@Bean(name = "makeJavaQuizJob")
	public Job makeJavaQuizJob() {
		return makeQuizFlow(jobBuilderFactory, apiJavaRequestStep(),
			converterFromResponseStep,
			categoryMapperStep,
			CategoryTitle.JAVA);
	}

	@Bean(name = "apiJavaRequestStep")
	public Step apiJavaRequestStep() {
		return stepBuilderFactory.get("apiJavaRequestStep")
			.tasklet(apiJavaRequestTasklet)
			.build();
	}
}
