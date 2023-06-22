package com.quizbatch.job.saverdbms;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveQuizAtRDBMS {

	private final JobBuilderFactory jobBuilderFactory;

	@Qualifier("saveQuizAtRDBMSStep")
	private final Step saveQuizAtRDBMSStep;

	/**
	 * RDBMS에 Quiz를 저장하는 Batch Job을 생성합니다.
	 *
	 * @return RDBMS에 Quiz 저장 Job
	 */
	@Bean(name = "saveQuizAtRDBMSJob")
	public Job saveQuizAtRDBMSJob() {
		return jobBuilderFactory.get("saveQuizAtRDBMSJob")
			.start(saveQuizAtRDBMSStep)
			.build();
	}

}
