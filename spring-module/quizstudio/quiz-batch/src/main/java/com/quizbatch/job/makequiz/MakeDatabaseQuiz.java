package com.quizbatch.job.makequiz;

import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MakeDatabaseQuiz {

	private final MakeQuizJobFlow makeQuizJobFlow;

	private final MakeQuizJobUtils makeQuizJobUtils;

	@Qualifier("apiDatabaseRequestStep")
	private final Step apiDatabaseRequestStep;


	/**
	 * Database Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Database Quiz 생성 Job
	 */
	@Bean(name = "makeDatabaseQuizJob")
	public Job makeDatabaseQuizJob() {
		return makeQuizJobFlow.makeQuizFlow(apiDatabaseRequestStep,
			makeQuizJobUtils.getConverterFromResponseStep(),
			makeQuizJobUtils.getCategoryMapperStep(), CategoryTitle.DATABASE);
	}
}
