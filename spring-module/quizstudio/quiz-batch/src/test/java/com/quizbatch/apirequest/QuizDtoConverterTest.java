package com.quizbatch.apirequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoConverter;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoFromResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuizDtoConverterTest {


	String responseJsonFromOPENAIAPI;

	@BeforeEach
	void setUp() {
		responseJsonFromOPENAIAPI = "["
			+ "{\"title\": \"4-3은?\", \"choices\": [{\"content\": \"1\", \"isAnswer\": true},{\"content\": \"2\", \"isAnswer\":false},{\"content\": \"5\", \"isAnswer\": false},{\"content\": \"4\", \"isAnswer\": false} ]},"
			+ "{\"title\": \"7-2은?\", \"choices\": [{\"content\": \"1\", \"isAnswer\": false},{\"content\": \"5\", \"isAnswer\":true},{\"content\": \"3\", \"isAnswer\": false},{\"content\": \"4\", \"isAnswer\": false} ]},"
			+ "{\"title\": \"8-4은?\", \"choices\": [{\"content\": \"3\", \"isAnswer\": false},{\"content\": \"2\", \"isAnswer\":false},{\"content\": \"5\", \"isAnswer\": false},{\"content\": \"4\", \"isAnswer\": true} ]}"
			+ "]";
	}


	@Test
	void 자바_카테고리와_OPENAI_API_로부터_온_JSON_데이터를_QuizSchema_로_Converter_해준다() {
		List<QuizDtoFromResponse> quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(
			responseJsonFromOPENAIAPI,
			CategoryTitle.JAVA);

		assertThat(quizDtoFromResponses.get(0).getCategoryTitle()).isEqualTo(CategoryTitle.JAVA);
	}

	@Test
	void 자료구조_카테고리와_OPENAI_API_로부터_온_JSON_데이터를_QuizSchema_로_Converter_해준다() {
		List<QuizDtoFromResponse> quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(
			responseJsonFromOPENAIAPI,
			CategoryTitle.DATA_STRUCTURE);

		assertThat(quizDtoFromResponses.get(0).getCategoryTitle()).isEqualTo(
			CategoryTitle.DATA_STRUCTURE);
	}

	@Test
	void 데이터베이스_카테고리와_OPENAI_API_로부터_온_JSON_데이터를_QuizSchema_로_Converter_해준다() {
		List<QuizDtoFromResponse> quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(
			responseJsonFromOPENAIAPI,
			CategoryTitle.DATABASE);

		assertThat(quizDtoFromResponses.get(0).getCategoryTitle()).isEqualTo(
			CategoryTitle.DATABASE);
	}

	@Test
	void 스프링_카테고리와_OPENAI_API_로부터_온_JSON_데이터를_QuizSchema_로_Converter_해준다() {
		List<QuizDtoFromResponse> quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(
			responseJsonFromOPENAIAPI,
			CategoryTitle.SPRING);

		assertThat(quizDtoFromResponses.get(0).getCategoryTitle()).isEqualTo(CategoryTitle.SPRING);
	}

	@Test
	void 네트워크_카테고리와_OPENAI_API_로부터_온_JSON_데이터를_QuizSchema_로_Converter_해준다() {
		List<QuizDtoFromResponse> quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(
			responseJsonFromOPENAIAPI,
			CategoryTitle.NETWORK);

		assertThat(quizDtoFromResponses.get(0).getCategoryTitle()).isEqualTo(CategoryTitle.NETWORK);
	}

	@Test
	void 인터뷰_카테고리와_OPENAI_API_로부터_온_JSON_데이터를_QuizSchema_로_Converter_해준다() {
		List<QuizDtoFromResponse> quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(
			responseJsonFromOPENAIAPI,
			CategoryTitle.INTERVIEW);

		assertThat(quizDtoFromResponses.get(0).getCategoryTitle()).isEqualTo(
			CategoryTitle.INTERVIEW);
	}


}
