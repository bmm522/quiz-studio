package quiz.service.quiz.mapper;

import java.util.List;
import java.util.stream.Collectors;
import quiz.domain.quiz.Quiz;
import quiz.global.dto.CustomQuizDto;
import quiz.repository.quiz.dto.QuizQueryDto;
import quiz.service.quiz.dto.S_QuizGetResponse;
import quiz.service.quiz.dto.S_QuizSaveResponse;

public class S_QuizMapper {

	public static S_QuizSaveResponse toSaveResponse(final String userKey,
		final List<Quiz> quizzes) {
		final List<CustomQuizDto> quizDtoList = quizzes.stream()
			.map(Quiz::toCustomQuizDto)
			.collect(Collectors.toList());
		return S_QuizSaveResponse.builder()
			.userKey(userKey)
			.quizzes(quizDtoList)
			.build();
	}

	public static S_QuizGetResponse toGetResponse(final List<QuizQueryDto> quizQueryDtos) {
		return S_QuizGetResponse.builder()
			.quizzes(quizQueryDtos)
			.build();
	}
}
