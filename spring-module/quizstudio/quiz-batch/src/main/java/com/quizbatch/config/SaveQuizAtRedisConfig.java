package com.quizbatch.config;

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
public class SaveQuizAtRedisConfig {

	private final StepBuilderFactory stepBuilderFactory;
	private final ClearQuizAtRedisTasklet clearQuizAtRedisTasklet;
	private final SaveQuizAtRedisTasklet saveQuizAtRedisTasklet;


	private final JobBuilderFactory jobBuilderFactory;

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
