package quiz.controller.quiz.mapper;

import java.util.List;
import quiz.controller.quiz.dto.QuizGetCondition;
import quiz.global.dto.CustomQuizDto;
import quiz.service.quiz.dto.QuizSaveParam;
import quiz.service.quiz.dto.QuizUpdateParam;

/**
 * 해당 클래스는 컨트롤러의 DTO를 서비스의 DTO로 변환해주는 기능을 제공합니다.
 */
public class ControllerQuizMapper {

	/**
	 * 퀴즈 저장 요청 데이터를 변환합니다.
	 *
	 * @param userKey    사용자 키
	 * @param categoryId 카테고리 ID
	 * @param quizzes    퀴즈 목록
	 * @return QuizSaveParam.Request 객체
	 */
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

	/**
	 * 퀴즈 업데이트 요청 데이터를 변환합니다.
	 *
	 * @param userKey    사용자 키
	 * @param categoryId 카테고리 ID
	 * @param quizzes    퀴즈 목록
	 * @return QuizUpdateParam.Request 객체
	 */
	public static QuizUpdateParam.Request toUpdateParam(
		final String userKey,
		final Long categoryId,
		final List<CustomQuizDto> quizzes) {

		for (CustomQuizDto dto : quizzes) {
			dto.checkCorrectAnswer();
		}
		return QuizUpdateParam.Request.builder()
			.userKey(userKey)
			.quizzes(quizzes)
			.categoryId(categoryId)
			.build();
	}

	/**
	 * 퀴즈 조회 조건을 변환합니다.
	 *
	 * @param userKey    사용자 키
	 * @param categoryId 카테고리 ID
	 * @param page       페이지 번호
	 * @return QuizGetCondition 객체
	 */
	public static QuizGetCondition toGetCondition(
		final String userKey,
		final Long categoryId,
		final int page) {

		return QuizGetCondition.builder()
			.userKey(userKey)
			.categoryId(categoryId)
			.page(page)
			.build();
	}

}
