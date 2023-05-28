package com.quizbatch.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class QuizMapper {

	public static List<Quiz> toQuizzes(final String responseJson) {

		Gson gson = new Gson();
		Type quizListType = new TypeToken<List<Quiz>>() {
		}.getType();

		List<Quiz> quizzes = gson.fromJson(responseJson, quizListType);
		for (Quiz quiz : quizzes) {
			quiz.setId("java", "easy");
		}
		return quizzes;
	}

}
