package com.quizbatch.config;


import com.quizbatch.tasklets.makequiz.step1apirequest.ApiRequestTasklet;
import com.quizbatch.tasklets.makequiz.step2converter.ConverterTasklet;
import com.quizbatch.tasklets.makequiz.step3mapper.MapperTasklet;
import com.quizbatch.tasklets.saverdbms.step1save.SaveQuizAtRDBMSTasklet;
import com.quizbatch.tasklets.saveredis.step1save.SaveQuizAtRedisTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@RequiredArgsConstructor
public class BatchConfig {


	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	private final ApiRequestTasklet apiRequestTasklet;

//	private final SaveQuizAtRedisTasklet saveAllRedisTasklet;

	private final SaveQuizAtRDBMSTasklet saveQuizAtRDBMSTasklet;

	private final MapperTasklet mapperTasklet;

	private final ConverterTasklet converterTasklet;

	private final SaveQuizAtRedisTasklet saveQuizAtRedisTasklet;

	@Bean
	public Job makeQuizJob() {
		return jobBuilderFactory.get("makeQuizJob")
			.start(apiRequestStep())
			.on("FAILED")
			.end()
			.from(apiRequestStep())
			.on("*")
			.to(converterFromResponseStep())
			.on("FAILED")
			.end()
			.from(converterFromResponseStep())
			.on("*")
			.to(mapperFromDtoStep())
			.on("FAILED")
			.end()
			.end()
			.build();
	}

	@Bean
	public Job saveQuizAtRDBMSJob() {
		return jobBuilderFactory.get("saveQuizAtRDBMSJob")
			.start(saveQuizAtRDBMSStep())
			.on("FAILED")
			.end()
			.end()
			.build();
	}

	@Bean
	public Job saveQuizAtRedisJob() {
		return jobBuilderFactory.get("saveQuizAtRedisJob")
			.start(saveQuizAtRedisStep())
			.on("FAILED")
			.end()
			.end()
			.build();
	}

	@Bean
	public Step saveQuizAtRedisStep() {
		return stepBuilderFactory.get("saveQuizAtRedisStep")
			.tasklet(saveQuizAtRedisTasklet)
			.build();
	}

	@Bean
	public Step saveQuizAtRDBMSStep() {
		return stepBuilderFactory.get(" saveQuizAtRDBMSStep")
			.tasklet(saveQuizAtRDBMSTasklet)
			.build();
	}


	@Bean
	public Step mapperFromDtoStep() {
		return stepBuilderFactory.get("mapperFromDtoStep")
			.tasklet(mapperTasklet)
			.build();
	}

	@Bean
	public Step apiRequestStep() {
		return stepBuilderFactory.get("apiRequestStep")
			.tasklet(apiRequestTasklet)
			.build();
	}

	@Bean
	public Step converterFromResponseStep() {
		return stepBuilderFactory.get("converterFromResponseStep")
			.tasklet(converterTasklet)
			.build();
	}


}
