package com.quizbatch.config;

import com.quizbatch.config.util.MakeQuizJobFlow;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiDatabaseRequestTasklet;
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
public class MakeDatabaseQuizConfig extends MakeQuizJobFlow {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	private final ApiDatabaseRequestTasklet apiDatabaseRequestTasklet;

	private final Step converterFromResponseStep;
	private final Step categoryMapperStep;

	/**
	 * Database Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Database Quiz 생성 Job
	 */
	@Bean(name = "makeDatabaseQuizJob")
	public Job makeDatabaseQuizJob() {
		return makeQuizFlow(jobBuilderFactory, apiDatabaseRequestStep(),
			converterFromResponseStep,
			categoryMapperStep, CategoryTitle.DATABASE);
	}

	@Bean(name = "apiDatabaseRequestStep")
	public Step apiDatabaseRequestStep() {
		return stepBuilderFactory.get("apiDatabaseRequestStep")
			.tasklet(apiDatabaseRequestTasklet)
			.build();
	}
}
