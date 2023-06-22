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
public class MakeInterviewQuiz {

	private final MakeQuizJobFlow makeQuizJobFlow;

	private final MakeQuizJobUtils makeQuizJobUtils;

	@Qualifier("apiInterviewRequestStep")
	private final Step apiInterviewRequestStep;


	/**
	 * Interview Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Interview Quiz 생성 Job
	 */
	@Bean(name = "makeInterviewQuizJob")
	public Job makeInterviewQuizJob() {
		return makeQuizJobFlow.makeQuizFlow(apiInterviewRequestStep,
			makeQuizJobUtils.getConverterFromResponseStep(),
			makeQuizJobUtils.getCategoryMapperStep(), CategoryTitle.INTERVIEW);
	}
}
