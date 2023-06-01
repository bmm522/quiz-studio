package quiz.service.quiz.mapper;

import java.util.List;
import java.util.stream.Collectors;
import quiz.domain.quiz.Quiz;
import quiz.global.dto.CustomQuizDto;
import quiz.repository.quiz.dto.QuizQueryDto;
import quiz.service.quiz.dto.QuizGetResponse;
import quiz.service.quiz.dto.QuizGetWithoutPagingResponse;
import quiz.service.quiz.dto.QuizSaveParam;

public class ServiceQuizMapper {

	/**
	 * 카테고리에 해당하는 퀴즈 저장 응답 객체로 변환합니다.
	 *
	 * @param userKey 사용자 키
	 * @param quizzes 퀴즈 목록
	 * @return 퀴즈 저장 응답 객체
	 */
	public static QuizSaveParam.Response toSaveResponse(final String userKey,
		final List<Quiz> quizzes) {
		final List<CustomQuizDto> quizDtoList = quizzes.stream()
			.map(Quiz::toCustomQuizDto)
			.collect(Collectors.toList());
		return QuizSaveParam.Response.builder()
			.userKey(userKey)
			.quizzes(quizDtoList)
			.build();
	}

	/**
	 * 퀴즈 조회 응답 객체로 변환합니다.
	 *
	 * @param quizQueryDtos  퀴즈의 쿼리 DTO 목록
	 * @param quizTotalCount 퀴즈의 총 개수
	 * @return 퀴즈 조회 응답 객체
	 */
	public static QuizGetResponse toGetResponse(
		final List<QuizQueryDto> quizQueryDtos,
		final Long quizTotalCount) {
		final int totalPage = (int) (Math.ceil((double) quizTotalCount / (double) 10));
		return QuizGetResponse.builder()
			.quizzes(quizQueryDtos)
			.totalCount(quizTotalCount)
			.totalPage(totalPage)
			.build();
	}

	/**
	 * 퀴즈 조회 응답 객체 (페이징 없음)로 변환합니다.
	 *
	 * @param quizQueryDtoList 퀴즈의 쿼리 DTO 목록
	 * @return 퀴즈 조회 응답 객체 (페이징 없음)
	 */
	public static QuizGetWithoutPagingResponse toGetWithOutPagingResponse(
		final List<QuizQueryDto> quizQueryDtoList) {
		return QuizGetWithoutPagingResponse.builder()
			.quizzes(quizQueryDtoList)
			.build();
	}
}
