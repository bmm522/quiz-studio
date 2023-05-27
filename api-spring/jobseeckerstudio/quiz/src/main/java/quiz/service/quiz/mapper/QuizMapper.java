package quiz.service.quiz.mapper;

import java.util.List;
import java.util.stream.Collectors;
import quiz.domain.quiz.Quiz;
import quiz.global.dto.CustomQuizDto;
import quiz.repository.quiz.dto.QuizQueryDto;
import quiz.service.quiz.dto.QuizGetResponse;
import quiz.service.quiz.dto.QuizGetWithoutPagingResponse;
import quiz.service.quiz.dto.QuizSaveParam;

public class QuizMapper {

	public static synchronized QuizSaveParam.Response toSaveResponse(final String userKey,
		final List<Quiz> quizzes) {
		final List<CustomQuizDto> quizDtoList = quizzes.stream()
			.map(Quiz::toCustomQuizDto)
			.collect(Collectors.toList());
		return QuizSaveParam.Response.builder()
			.userKey(userKey)
			.quizzes(quizDtoList)
			.build();
	}

	public static synchronized QuizGetResponse toGetResponse(
		final List<QuizQueryDto> quizQueryDtos,
		final Long quizTotalCount) {
		final int totalPage = (int) (Math.ceil((double) quizTotalCount / (double) 10));
		return QuizGetResponse.builder()
			.quizzes(quizQueryDtos)
			.totalCount(quizTotalCount)
			.totalPage(totalPage)
			.build();
	}

	public static synchronized QuizGetWithoutPagingResponse toGetWithOutPagingResponse(
		final List<QuizQueryDto> quizQueryDtoList) {
		return QuizGetWithoutPagingResponse.builder()
			.quizzes(quizQueryDtoList)
			.build();
	}
}
