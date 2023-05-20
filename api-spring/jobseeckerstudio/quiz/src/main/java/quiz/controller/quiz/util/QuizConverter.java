package quiz.controller.quiz.util;

import java.util.List;
import quiz.global.dto.CustomQuizDto;
import quiz.service.quiz.dto.QuizSaveParam;

public class QuizConverter {

	public static QuizSaveParam.Request toSaveParam(
		final String userKey,
		final List<CustomQuizDto> quizDtoList,
		final Long userCategoryId) {
		for (CustomQuizDto dto : quizDtoList) {
			dto.checkCorrectAnswer();
		}
		return QuizSaveParam.Request.builder()
			.userKey(userKey)
			.quizzes(quizDtoList)
			.categoryId(userCategoryId)
			.build();
	}
}
