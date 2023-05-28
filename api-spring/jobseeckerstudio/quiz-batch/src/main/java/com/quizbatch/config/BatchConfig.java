package com.quizbatch.config;


import com.quizbatch.tasklets.ApiRequestTasklet;
import com.quizbatch.tasklets.MapperTasklet;
import com.quizbatch.tasklets.SaveAllRedisTasklet;
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

	private final ApiRequestTasklet apiRequestTasklet;

	private final SaveAllRedisTasklet saveAllRedisTasklet;

	@Bean
	public Job testJob() {
		return jobBuilderFactory.get("testJob")
			.start(apiRequestStep())
			.on("FAILED")
			.end()
			.from(apiRequestStep())
			.on("*")
			.to(mapperStep())
			.on("FAILED")
			.end()
			.from(mapperStep())
			.on("*")
			.to(saveAllStep())
			.on("FAILED")
			.end()
			.end()
			.build();
	}

	@Bean
	public Step saveAllStep() {
		return stepBuilderFactory.get("saveAllStep")
			.tasklet(saveAllRedisTasklet)
			.build();
	}

	@Bean
	public Step apiRequestStep() {
		return stepBuilderFactory.get("apiRequestStep")
			.tasklet(apiRequestTasklet)
			.build();
	}

	@Bean
	public Step mapperStep() {
		return stepBuilderFactory.get("mapperStep")
			.tasklet(new MapperTasklet())
			.build();
	}


}
