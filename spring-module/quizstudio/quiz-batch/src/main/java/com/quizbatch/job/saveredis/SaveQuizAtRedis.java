package com.quizbatch.job.saveredis;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveQuizAtRedis {

	private final JobBuilderFactory jobBuilderFactory;

	@Qualifier("clearQuizAtRedisStep")
	private final Step clearQuizAtRedisStep;

	@Qualifier("saveQuizAtRedisStep")
	private final Step saveQuizAtRedisStep;

	/**
	 * Redis에 Quiz를 저장하는 Batch Job을 생성합니다.
	 *
	 * @return Redis에 Quiz 저장 Job
	 */

	@Bean(name = "saveQuizAtRedisJob")
	public Job saveQuizAtRedisJob() {
		return jobBuilderFactory.get("saveQuizAtRedisJob")
			.start(clearQuizAtRedisStep)
			.on("FAILED").end()
			.from(clearQuizAtRedisStep)
			.on("*").to(saveQuizAtRedisStep)
			.on("FAILED").end().end()
			.build();
	}
}
