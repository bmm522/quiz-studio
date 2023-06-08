package com.quizbatch.config;

import com.quizbatch.tasklets.makequiz.step1apirequest.ApiDataStructureRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiDatabaseRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiJavaRequestTasklet;
import com.quizbatch.tasklets.makequiz.step2converter.ConverterTasklet;
import com.quizbatch.tasklets.makequiz.step3mapper.DataStructureCategoryMapperTasklet;
import com.quizbatch.tasklets.makequiz.step3mapper.DatabaseCategoryMapperTasklet;
import com.quizbatch.tasklets.makequiz.step3mapper.JavaCategoryMapperTasklet;
import com.quizbatch.tasklets.saverdbms.step1save.SaveQuizAtRDBMSTasklet;
import com.quizbatch.tasklets.saveredis.step1clearAll.ClearQuizAtRedisTasklet;
import com.quizbatch.tasklets.saveredis.step2save.SaveQuizAtRedisTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	private final ApiJavaRequestTasklet apiJavaRequestTasklet;
	private final ApiDataStructureRequestTasklet apiDataStructureRequestTasklet;
	private final ApiDatabaseRequestTasklet apiDatabaseRequestTasklet;
	private final SaveQuizAtRDBMSTasklet saveQuizAtRDBMSTasklet;
	private final DatabaseCategoryMapperTasklet databaseCategoryMapperTasklet;
	private final JavaCategoryMapperTasklet javaCategoryMapperTasklet;
	private final DataStructureCategoryMapperTasklet dataStructureCategoryMapperTasklet;

	private final ClearQuizAtRedisTasklet clearQuizAtRedisTasklet;
	private final SaveQuizAtRedisTasklet saveQuizAtRedisTasklet;
	private final ConverterTasklet converterTasklet;

	/**
	 * Java Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Java Quiz 생성 Job
	 */
	@Bean(name = "makeJavaQuizJob")
	public Job makeJavaQuizJob() {
		return jobBuilderFactory.get("makeJavaQuizJob")
			.start(apiJavaRequestStep())
			.on("FAILED").end()
			.from(apiJavaRequestStep())
			.on("*").to(converterFromResponseStep())
			.on("FAILED").end()
			.from(converterFromResponseStep())
			.on("*").to(javaCategoryMapperStep())
			.on("FAILED").end()
			.end()
			.build();
	}

	/**
	 * Data Structure Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Data Structure Quiz 생성 Job
	 */
	@Bean(name = "makeDataStructureQuizJob")
	public Job makeDataStructureQuizJob() {
		return jobBuilderFactory.get("makeDataStructureQuizJob")
			.start(apiDataStructureRequestStep())
			.on("FAILED").end()
			.from(apiDataStructureRequestStep())
			.on("*").to(converterFromResponseStep())
			.on("FAILED").end()
			.from(converterFromResponseStep())
			.on("*").to(dataStructureCategoryMapperStep())
			.on("FAILED").end()
			.end()
			.build();
	}

	/**
	 * Database Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Database Quiz 생성 Job
	 */
	@Bean(name = "makeDatabaseQuizJob")
	public Job makeDatabaseQuizJob() {
		return jobBuilderFactory.get("makeDatabaseQuizJob")
			.start(apiDatabaseRequestStep())
			.on("FAILED").end()
			.from(apiDatabaseRequestStep())
			.on("*").to(converterFromResponseStep())
			.on("FAILED").end()
			.from(converterFromResponseStep())
			.on("*").to(databaseCategoryMapperStep())
			.on("FAILED").end()
			.end()
			.build();
	}


	/**
	 * RDBMS에 Quiz를 저장하는 Batch Job을 생성합니다.
	 *
	 * @return RDBMS에 Quiz 저장 Job
	 */
	@Bean(name = "saveQuizAtRDBMSJob")
	public Job saveQuizAtRDBMSJob() {
		return jobBuilderFactory.get("saveQuizAtRDBMSJob")
			.start(saveQuizAtRDBMSStep())
			.build();
	}

	/**
	 * Redis에 Quiz를 저장하는 Batch Job을 생성합니다.
	 *
	 * @return Redis에 Quiz 저장 Job
	 */
	@Bean(name = "saveQuizAtRedisJob")
	public Job saveQuizAtRedisJob() {
		return jobBuilderFactory.get("saveQuizAtRedisJob")
			.start(clearQuizAtRedisStep())
			.on("FAILED").end()
			.from(clearQuizAtRedisStep())
			.on("*").to(saveQuizAtRedisStep())
			.on("FAILED").end().end()
			.build();
	}


	@Bean
	public Step saveQuizAtRDBMSStep() {
		return stepBuilderFactory.get("saveQuizAtRDBMSStep")
			.tasklet(saveQuizAtRDBMSTasklet)
			.build();
	}

	@Bean
	public Step clearQuizAtRedisStep() {
		return stepBuilderFactory.get("clearQuizAtRedisStep")
			.tasklet(clearQuizAtRedisTasklet)
			.build();
	}

	@Bean
	public Step saveQuizAtRedisStep() {
		return stepBuilderFactory.get("saveQuizAtRedisStep")
			.tasklet(saveQuizAtRedisTasklet)
			.build();
	}

	@Bean
	public Step javaCategoryMapperStep() {
		return stepBuilderFactory.get("javaCategoryMapperStep")
			.tasklet(javaCategoryMapperTasklet)
			.build();
	}

	@Bean
	public Step dataStructureCategoryMapperStep() {
		return stepBuilderFactory.get("dataStructureCategoryMapperStep")
			.tasklet(dataStructureCategoryMapperTasklet)
			.build();
	}

	@Bean
	public Step databaseCategoryMapperStep() {
		return stepBuilderFactory.get("databaseCategoryMapperStep")
			.tasklet(databaseCategoryMapperTasklet)
			.build();
	}

	@Bean
	public Step apiJavaRequestStep() {
		return stepBuilderFactory.get("apiJavaRequestStep")
			.tasklet(apiJavaRequestTasklet)
			.build();
	}

	@Bean
	public Step apiDataStructureRequestStep() {
		return stepBuilderFactory.get("apiDataStructureRequestStep")
			.tasklet(apiDataStructureRequestTasklet)
			.build();
	}

	@Bean
	public Step apiDatabaseRequestStep() {
		return stepBuilderFactory.get("apiDatabaseRequestStep")
			.tasklet(apiDatabaseRequestTasklet)
			.build();
	}

	@Bean
	public Step converterFromResponseStep() {
		return stepBuilderFactory.get("converterFromResponseStep")
			.tasklet(converterTasklet)
			.build();
	}
}
