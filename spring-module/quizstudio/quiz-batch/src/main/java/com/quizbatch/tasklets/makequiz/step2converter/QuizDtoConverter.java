package com.quizbatch.tasklets.makequiz.step2converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quizbatch.domain.quizschema.QuizSchema;
import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class QuizDtoConverter {

	/**
	 * API 응답 JSON을 퀴즈 스키마 리스트로 변환하는 메서드입니다.
	 *
	 * @param responseJson API 응답 JSON
	 * @return 퀴즈 스키마 리스트
	 */
	public static List<QuizSchema> toQuizzes(final String responseJson) {

		Gson gson = new Gson();
		Type quizListType = new TypeToken<List<QuizSchema>>() {
		}.getType();

		List<QuizSchema> quizSchemas = gson.fromJson(responseJson, quizListType);
		for (QuizSchema quizSchema : quizSchemas) {
			quizSchema.setId("java");
		}
		return quizSchemas;
	}

	/**
	 * API 응답을 퀴즈 DTO 리스트로 변환하는 메서드입니다.
	 *
	 * @param response      API 응답
	 * @param categoryTitle 카테고리 타이틀
	 * @return 퀴즈 DTO 리스트
	 */
	public static List<QuizDtoFromResponse> toQuizDtosFromResponses(final String response,
		final CategoryTitle categoryTitle) {
		Gson gson = new Gson();

		Type quizListType = new TypeToken<List<QuizDtoFromResponse>>() {
		}.getType();

		List<QuizDtoFromResponse> quizDtoFromResponses = gson.fromJson(response, quizListType);
		for (QuizDtoFromResponse dto : quizDtoFromResponses) {
			dto.setCategoryTitle(categoryTitle);
			dto.setId();
		}
		return quizDtoFromResponses;
	}
}
