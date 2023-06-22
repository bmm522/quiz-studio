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
public class MakeDataStructureQuiz {


	private final MakeQuizJobFlow makeQuizJobFlow;

	private final MakeQuizJobUtils makeQuizJobUtils;
	@Qualifier("apiDataStructureRequestStep")
	private final Step apiDataStructureRequestStep;

	/**
	 * Data Structure Quiz 생성 작업을 수행하는 Batch Job을 생성합니다.
	 *
	 * @return Data Structure Quiz 생성 Job
	 */
	@Bean(name = "makeDataStructureQuizJob")
	public Job makeDataStructureQuizJob() {
		return makeQuizJobFlow.makeQuizFlow(apiDataStructureRequestStep,
			makeQuizJobUtils.getConverterFromResponseStep(),
			makeQuizJobUtils.getCategoryMapperStep(), CategoryTitle.DATA_STRUCTURE);
	}

}
