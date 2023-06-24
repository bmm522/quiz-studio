package com.quizbatch.job;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.quizbatch.config.util.MakeQuizJobFlow;
import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;

public class MakeQuizJobFlowTest {

	@Mock
	JobBuilderFactory jobBuilderFactory;

	@Mock
	private Step apiRequestStep;

	@Mock
	private Step converterFromResponseStep;

	@Mock
	private Step categoryMapperStep;


	private MakeQuizJobFlow makeQuizJobFlow;

	@BeforeEach
	void init() {
		makeQuizJobFlow = new MakeQuizJobFlow();
	}


	@Test
	void JAVA_카테고리를_넣으면_JAVA에_해당되는_Job_이름을_세팅해준다() {
		String jobName = makeQuizJobFlow.getJobName(CategoryTitle.JAVA);

		assertThat(jobName).isEqualTo("makeJavaQuizJob");
	}

	@Test
	void DATA_STURCTURE_카테고리를_넣으면_DATA_STURCTURE에_해당되는_Job_이름을_세팅해준다() {
		String jobName = makeQuizJobFlow.getJobName(CategoryTitle.DATA_STRUCTURE);

		assertThat(jobName).isEqualTo("makeDataStructureQuizJob");
	}

	@Test
	void DATABASE_카테고리를_넣으면_DATABASE에_해당되는_Job_이름을_세팅해준다() {
		String jobName = makeQuizJobFlow.getJobName(CategoryTitle.DATABASE);

		assertThat(jobName).isEqualTo("makeDatabaseQuizJob");
	}

	@Test
	void SPRING_카테고리를_넣으면_SPRING에_해당되는_Job_이름을_세팅해준다() {
		String jobName = makeQuizJobFlow.getJobName(CategoryTitle.SPRING);

		assertThat(jobName).isEqualTo("makeSpringQuizJob");
	}

	@Test
	void NETWORK_카테고리를_넣으면_NETWORK에_해당되는_Job_이름을_세팅해준다() {
		String jobName = makeQuizJobFlow.getJobName(CategoryTitle.NETWORK);

		assertThat(jobName).isEqualTo("makeNetworkQuizJob");
	}

	@Test
	void INTERVIEW_카테고리를_넣으면_INTERVIEW에_해당되는_Job_이름을_세팅해준다() {
		String jobName = makeQuizJobFlow.getJobName(CategoryTitle.INTERVIEW);

		assertThat(jobName).isEqualTo("makeInterviewQuizJob");
	}


}
