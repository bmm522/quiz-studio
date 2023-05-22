package quiz.controller.quiz.util;

import java.util.List;
import quiz.global.dto.CustomQuizDto;
import quiz.service.quiz.dto.QuizSaveParam;
import quiz.service.quiz.dto.QuizUpdateParam;

public class QuizConverter {

	public static QuizSaveParam.Request toSaveParam(
		final String userKey,
		final Long categoryId,
		final List<CustomQuizDto> quizzes) {
		for (CustomQuizDto dto : quizzes) {
			dto.checkCorrectAnswer();
		}
		return QuizSaveParam.Request.builder()
			.userKey(userKey)
			.quizzes(quizzes)
			.categoryId(categoryId)
			.build();
	}

	public static QuizUpdateParam.Request toUpdateParam(
		String userKey,
		Long categoryId,
		List<CustomQuizDto> quizzes) {

		for (CustomQuizDto dto : quizzes) {
			dto.checkCorrectAnswer();
		}
		return QuizUpdateParam.Request.builder()
			.userKey(userKey)
			.quizzes(quizzes)
			.categoryId(categoryId)
			.build();
	}
}
