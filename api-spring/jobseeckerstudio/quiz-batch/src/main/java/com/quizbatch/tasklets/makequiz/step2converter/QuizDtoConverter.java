package com.quizbatch.tasklets.makequiz.step2converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quizbatch.domain.quizschema.QuizSchema;
import java.lang.reflect.Type;
import java.util.List;

public class QuizDtoConverter {

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

	public static List<QuizDtoFromResponse> toQuizDtosFromResponses(String response) {
		Gson gson = new Gson();

		Type quizListType = new TypeToken<List<QuizDtoFromResponse>>() {
		}.getType();

		List<QuizDtoFromResponse> quizDtoFromResponses = gson.fromJson(response, quizListType);
		for (QuizDtoFromResponse dto : quizDtoFromResponses) {
			dto.setId("java");
		}
		return quizDtoFromResponses;
	}
}
