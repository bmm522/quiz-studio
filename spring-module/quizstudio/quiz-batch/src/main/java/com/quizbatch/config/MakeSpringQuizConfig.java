package com.quizbatch.config;

import com.quizbatch.config.util.MakeQuizJobFlow;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiSpringRequestTasklet;
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
public class MakeSpringQuizConfig extends MakeQuizJobFlow {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	private final ApiSpringRequestTasklet apiSpringRequestTasklet;

	private final Step converterFromResponseStep;
	private final Step categoryMapperStep;

	/**
	 * Spring Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Spring Quiz 생성 Job
	 */
	@Bean(name = "makeSpringQuizJob")
	public Job makeSpringQuizJob() {
		return makeQuizFlow(jobBuilderFactory, apiSpringRequestStep(),
			converterFromResponseStep,
			categoryMapperStep, CategoryTitle.SPRING);
	}

	@Bean(name = "apiSpringRequestStep")
	public Step apiSpringRequestStep() {
		return stepBuilderFactory.get("apiSpringRequestStep")
			.tasklet(apiSpringRequestTasklet)
			.build();
	}
}
