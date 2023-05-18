package quiz.controller.quiz.mapper;

import java.util.List;
import quiz.controller.quiz.dto.C_QuizSaveRequest;
import quiz.global.dto.CustomQuizDto;
import quiz.global.exception.NotCorrectAnswerException;
import quiz.service.quiz.dto.S_QuizSaveRequest;

public class C_QuizMapper {

	public static S_QuizSaveRequest toSaveRequest(final String userKey,
		final C_QuizSaveRequest request,
		final Long userCategoryId) {
		checkCorrectAnswer(request.getQuizzes());
		return S_QuizSaveRequest.builder()
			.userKey(userKey)
			.quizzes(request.getQuizzes())
			.categoryId(userCategoryId)
			.build();
	}

	public static void checkCorrectAnswer(final List<CustomQuizDto> customQuizDtoList) {
		for (int i = 0; i < customQuizDtoList.size(); i++) {
			long correctAnswerCount = customQuizDtoList.get(i).getChoices().stream()
				.filter(CustomQuizDto.Choice::getIsAnswer)
				.count();

			if (correctAnswerCount != 1) {
				throw new NotCorrectAnswerException(
					"정확히 하나의 답만 선택해야 합니다. 퀴즈의 현재 선택된 답안 수 : " + correctAnswerCount
				);
			}
		}
	}
}
