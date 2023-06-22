package com.quizbatch.apirequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.quizbatch.tasklets.makequiz.step1apirequest.ApiDataStructureRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiDatabaseRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiInterviewRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiJavaRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiNetworkRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiRequestAbstractTemplate;
import com.quizbatch.tasklets.makequiz.step1apirequest.ApiSpringRequestTasklet;
import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

public class ApiRequestGetTitleTest {


	@Mock
	private RestTemplate restTemplate;
	private ApiRequestAbstractTemplate apiRequestTasklet;


	@Test
	void 자바_카테고리를_가져온다() {
		apiRequestTasklet = new ApiJavaRequestTasklet(restTemplate);
		CategoryTitle categoryTitle = apiRequestTasklet.getCategoryTitle();

		assertThat(categoryTitle).isEqualTo(CategoryTitle.JAVA);
	}

	@Test
	void 자료구조_카테고리를_가져온다() {
		apiRequestTasklet = new ApiDataStructureRequestTasklet(restTemplate);
		CategoryTitle categoryTitle = apiRequestTasklet.getCategoryTitle();

		assertThat(categoryTitle).isEqualTo(CategoryTitle.DATA_STRUCTURE);
	}

	@Test
	void 데이터베이스_카테고리를_가져온다() {
		apiRequestTasklet = new ApiDatabaseRequestTasklet(restTemplate);
		CategoryTitle categoryTitle = apiRequestTasklet.getCategoryTitle();

		assertThat(categoryTitle).isEqualTo(CategoryTitle.DATABASE);
	}

	@Test
	void 스프링_카테고리를_가져온다() {
		apiRequestTasklet = new ApiSpringRequestTasklet(restTemplate);
		CategoryTitle categoryTitle = apiRequestTasklet.getCategoryTitle();

		assertThat(categoryTitle).isEqualTo(CategoryTitle.SPRING);
	}

	@Test
	void 네트워크_카테고리를_가져온다() {
		apiRequestTasklet = new ApiNetworkRequestTasklet(restTemplate);
		CategoryTitle categoryTitle = apiRequestTasklet.getCategoryTitle();

		assertThat(categoryTitle).isEqualTo(CategoryTitle.NETWORK);
	}

	@Test
	void 인터뷰_카테고리를_가져온다() {
		apiRequestTasklet = new ApiInterviewRequestTasklet(restTemplate);
		CategoryTitle categoryTitle = apiRequestTasklet.getCategoryTitle();

		assertThat(categoryTitle).isEqualTo(CategoryTitle.INTERVIEW);
	}

}
