package com.quizbatch.config;

import com.quizbatch.tasklets.makequiz.step1apirequest.ApiDataStructureRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiDatabaseRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiInterviewRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiJavaRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiNetworkRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiSpringRequestTasklet;
import com.quizbatch.tasklets.makequiz.step2converter.ConverterTasklet;
import com.quizbatch.tasklets.makequiz.step3mapper.CategoryMapperTasklet;
import com.quizbatch.tasklets.saverdbms.step1save.SaveQuizAtRDBMSTasklet;
import com.quizbatch.tasklets.saveredis.step1clearAll.ClearQuizAtRedisTasklet;
import com.quizbatch.tasklets.saveredis.step2save.SaveQuizAtRedisTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {


	private final StepBuilderFactory stepBuilderFactory;


	private final ApiJavaRequestTasklet apiJavaRequestTasklet;
	private final ConverterTasklet converterTasklet;

	private final CategoryMapperTasklet categoryMapperTasklet;

	private final ApiDataStructureRequestTasklet apiDataStructureRequestTasklet;
	private final ApiDatabaseRequestTasklet apiDatabaseRequestTasklet;

	private final ApiSpringRequestTasklet apiSpringRequestTasklet;

	private final ApiNetworkRequestTasklet apiNetworkRequestTasklet;

	private final ApiInterviewRequestTasklet apiInterviewRequestTasklet;
	private final SaveQuizAtRDBMSTasklet saveQuizAtRDBMSTasklet;


	private final ClearQuizAtRedisTasklet clearQuizAtRedisTasklet;
	private final SaveQuizAtRedisTasklet saveQuizAtRedisTasklet;

	/**
	 * Data Structure Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Data Structure Quiz 생성 Job
	 */
//	@Bean(name = "makeDataStructureQuizJob")
//	public Job makeDataStructureQuizJob() {
//		return jobBuilderFactory.get("makeDataStructureQuizJob")
//			.start(apiDataStructureRequestStep())
//			.on("FAILED").end()
//			.from(apiDataStructureRequestStep())
//			.on("*").to(converterFromResponseStep())
//			.on("FAILED").end()
//			.from(converterFromResponseStep())
//			.on("*").to(categoryMapperStep())
//			.on("FAILED").end()
//			.end()
//			.build();
//	}

	/**
	 * Database Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Database Quiz 생성 Job
	 */
//	@Bean(name = "makeDatabaseQuizJob")
//	public Job makeDatabaseQuizJob() {
//		return jobBuilderFactory.get("makeDatabaseQuizJob")
//			.start(apiDatabaseRequestStep())
//			.on("FAILED").end()
//			.from(apiDatabaseRequestStep())
//			.on("*").to(converterFromResponseStep())
//			.on("FAILED").end()
//			.from(converterFromResponseStep())
//			.on("*").to(categoryMapperStep())
//			.on("FAILED").end()
//			.end()
//			.build();
//	}

//	@Bean(name = "makeSpringQuizJob")
//	public Job makeSpringQuizJob() {
//		return jobBuilderFactory.get("makeSpringQuizJob")
//			.start(apiSpringRequestStep())
//			.on("FAILED").end()
//			.from(apiSpringRequestStep())
//			.on("*").to(converterFromResponseStep())
//			.on("FAILED").end()
//			.from(converterFromResponseStep())
//			.on("*").to(categoryMapperStep())
//			.on("FAILED").end()
//			.end()
//			.build();
//	}

//	@Bean(name = "makeNetworkQuizJob")
//	public Job makeNetworkQuizJob() {
//		return jobBuilderFactory.get("makeNetworkQuizJob")
//			.start(apiSpringRequestStep())
//			.on("FAILED").end()
//			.from(apiSpringRequestStep())
//			.on("*").to(converterFromResponseStep())
//			.on("FAILED").end()
//			.from(converterFromResponseStep())
//			.on("*").to(categoryMapperStep())
//			.on("FAILED").end()
//			.end()
//			.build();
//	}

//	@Bean(name = "makeInterviewQuizJob")
//	public Job makeInterviewQuizJob() {
//		return jobBuilderFactory.get("makeInterviewQuizJob")
//			.start(apiSpringRequestStep())
//			.on("FAILED").end()
//			.from(apiSpringRequestStep())
//			.on("*").to(converterFromResponseStep())
//			.on("FAILED").end()
//			.from(converterFromResponseStep())
//			.on("*").to(categoryMapperStep())
//			.on("FAILED").end()
//			.end()
//			.build();
//	}

	/**
	 * RDBMS에 Quiz를 저장하는 Batch Job을 생성합니다.
	 *
	 * @return RDBMS에 Quiz 저장 Job
	 */
//	@Bean(name = "saveQuizAtRDBMSJob")
//	public Job saveQuizAtRDBMSJob() {
//		return jobBuilderFactory.get("saveQuizAtRDBMSJob")
//			.start(saveQuizAtRDBMSStep())
//			.build();
//	}

	/**
	 * Redis에 Quiz를 저장하는 Batch Job을 생성합니다.
	 *
	 * @return Redis에 Quiz 저장 Job
	 */
//	@Bean(name = "saveQuizAtRedisJob")
//	public Job saveQuizAtRedisJob() {
//		return jobBuilderFactory.get("saveQuizAtRedisJob")
//			.start(clearQuizAtRedisStep())
//			.on("FAILED").end()
//			.from(clearQuizAtRedisStep())
//			.on("*").to(saveQuizAtRedisStep())
//			.on("FAILED").end().end()
//			.build();
//	}
	@Bean(name = "apiJavaRequestStep")
	public Step apiJavaRequestStep() {
		return stepBuilderFactory.get("apiJavaRequestStep")
			.tasklet(apiJavaRequestTasklet)
			.build();
	}

	@Bean(name = "apiDataStructureRequestStep")
	public Step apiDataStructureRequestStep() {
		return stepBuilderFactory.get("apiDataStructureRequestStep")
			.tasklet(apiDataStructureRequestTasklet)
			.build();
	}

	@Bean(name = "apiDatabaseRequestStep")
	public Step apiDatabaseRequestStep() {
		return stepBuilderFactory.get("apiDatabaseRequestStep")
			.tasklet(apiDatabaseRequestTasklet)
			.build();
	}

	@Bean(name = "apiSpringRequestStep")
	public Step apiSpringRequestStep() {
		return stepBuilderFactory.get("apiSpringRequestStep")
			.tasklet(apiSpringRequestTasklet)
			.build();
	}

	@Bean(name = "apiNetworkRequestStep")
	public Step apiNetworkRequestStep() {
		return stepBuilderFactory.get("apiNetworkRequestStep")
			.tasklet(apiNetworkRequestTasklet)
			.build();
	}

	@Bean(name = "apiInterviewRequestStep")
	public Step apiInterviewRequestStep() {
		return stepBuilderFactory.get("apiInterviewRequestStep")
			.tasklet(apiInterviewRequestTasklet)
			.build();
	}

	@Bean(name = "converterFromResponseStep")
	public Step converterFromResponseStep() {
		return stepBuilderFactory.get("converterFromResponseStep")
			.tasklet(converterTasklet)
			.build();
	}

	@Bean(name = "categoryMapperStep")
	public Step categoryMapperStep() {
		return stepBuilderFactory.get("categoryMapperStep")
			.tasklet(categoryMapperTasklet)
			.build();
	}

	@Bean(name = "saveQuizAtRDBMSStep")
	public Step saveQuizAtRDBMSStep() {
		return stepBuilderFactory.get("saveQuizAtRDBMSStep")
			.tasklet(saveQuizAtRDBMSTasklet)
			.build();
	}

	@Bean(name = "clearQuizAtRedisStep")
	public Step clearQuizAtRedisStep() {
		return stepBuilderFactory.get("clearQuizAtRedisStep")
			.tasklet(clearQuizAtRedisTasklet)
			.build();
	}


	@Bean(name = "saveQuizAtRedisStep")
	public Step saveQuizAtRedisStep() {
		return stepBuilderFactory.get("saveQuizAtRedisStep")
			.tasklet(saveQuizAtRedisTasklet)
			.build();
	}


}
