package com.quizbatch.config;

import com.quizbatch.tasklets.saverdbms.step1save.SaveQuizAtRDBMSTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SaveQuizAtRDBMSConfig {


	private final JobBuilderFactory jobBuilderFactory;

	private final StepBuilderFactory stepBuilderFactory;

	private final SaveQuizAtRDBMSTasklet saveQuizAtRDBMSTasklet;

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

	@Bean(name = "saveQuizAtRDBMSStep")
	public Step saveQuizAtRDBMSStep() {
		return stepBuilderFactory.get("saveQuizAtRDBMSStep")
			.tasklet(saveQuizAtRDBMSTasklet)
			.build();
	}


}
