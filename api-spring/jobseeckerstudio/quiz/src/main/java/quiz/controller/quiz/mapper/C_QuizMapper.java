package quiz.controller.quiz.mapper;

import quiz.controller.quiz.dto.C_QuizSaveRequest;
import quiz.global.dto.CustomQuizDto;
import quiz.global.exception.NotCorrectAnswerException;
import quiz.service.quiz.dto.S_QuizSaveRequest;

public class C_QuizMapper {

	public static S_QuizSaveRequest toSaveRequest(String userKey, C_QuizSaveRequest request,
		long categoryId) {
		checkCorrectAnswer(request);
		return S_QuizSaveRequest.builder()
			.userKey(userKey)
			.quizzes(request.getQuizzes())
			.categoryId(categoryId)
			.build();
	}

	private static void checkCorrectAnswer(C_QuizSaveRequest request) {
		for (int i = 0; i < request.getQuizzes().size(); i++) {
			long correctAnswerCount = request.getQuizzes().get(i).getChoices().stream()
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
