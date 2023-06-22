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
public class MakeJavaQuiz {


	private final MakeQuizJobFlow makeQuizJobFlow;

	private final MakeQuizJobUtils makeQuizJobUtils;

	@Qualifier("apiJavaRequestStep")
	private final Step apiJavaRequestStep;


	/**
	 * Java Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Java Quiz 생성 Job
	 */
	@Bean(name = "makeJavaQuizJob")
	public Job makeJavaQuizJob() {
		return makeQuizJobFlow.makeQuizFlow(apiJavaRequestStep,
			makeQuizJobUtils.getConverterFromResponseStep(),
			makeQuizJobUtils.getCategoryMapperStep(),
			CategoryTitle.JAVA);
	}


}
