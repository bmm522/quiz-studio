package quiz.controller.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quiz.controller.dto.CommonResponse;
import quiz.controller.dto.ResponseHandler;
import quiz.controller.quiz.dto.QuizSaveBody;
import quiz.controller.quiz.dto.QuizUpdateBody;
import quiz.controller.quiz.mapper.ControllerQuizMapper;
import quiz.service.quiz.QuizService;
import quiz.service.quiz.dto.QuizGetResponse;
import quiz.service.quiz.dto.QuizGetWithoutPagingResponse;
import quiz.service.quiz.dto.QuizSaveParam;
import quiz.service.quiz.dto.QuizUpdateParam;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class QuizController {

	private final QuizService quizService;

	/**
	 * 퀴즈 저장 메서드
	 *
	 * @param userKey    사용자 키
	 * @param categoryId 카테고리 ID
	 * @param body       퀴즈 저장 요청 정보
	 * @return CommonResponse 객체
	 */
	@PostMapping("/category/{categoryId}/quiz")
	public CommonResponse<?> saveQuiz(
		@RequestAttribute("userKey") final String userKey,
		@PathVariable("categoryId") final long categoryId,
		@RequestBody final QuizSaveBody body) {
		final QuizSaveParam.Request request = ControllerQuizMapper.toSaveParam(userKey, categoryId,
			body.getQuizzes());
		final QuizSaveParam.Response response = quizService.saveAll(request);
		return ResponseHandler.handle(HttpStatus.CREATED.value(), "퀴즈 저장 성공", response);
	}

	/**
	 * 페이징을 적용한 퀴즈 조회 메서드
	 *
	 * @param userKey    사용자 키
	 * @param categoryId 카테고리 ID
	 * @param page       페이지 번호
	 * @return CommonResponse 객체
	 */
	@GetMapping("/category/{categoryId}/quiz")
	public CommonResponse<?> getQuizzesWithPaging(
		@RequestAttribute("userKey") final String userKey,
		@PathVariable("categoryId") final long categoryId,
		@RequestParam("page") final int page) {
		final QuizGetResponse response = quizService.getQuizzesWithPaging(userKey, categoryId,
			page);
		return ResponseHandler.handle(HttpStatus.OK.value(), "퀴즈 불러오기 성공", response);
	}

	/**
	 * 퀴즈 조회(퀴즈 풀기용) 메서드
	 *
	 * @param userKey    사용자 키
	 * @param categoryId 카테고리 ID
	 * @return CommonResponse 객체
	 */
	@GetMapping("/category/{categoryId}/take-quiz")
	public CommonResponse<?> getQuizzesWhenTakeQuiz(
		@RequestAttribute("userKey") final String userKey,
		@PathVariable("categoryId") final long categoryId) {
		final QuizGetWithoutPagingResponse response = quizService.getQuizzesWhenTakeQuiz(userKey,
			categoryId);
		return ResponseHandler.handle(HttpStatus.OK.value(), "퀴즈 불러오기 성공", response);
	}

	/**
	 * 퀴즈 업데이트 메서드
	 *
	 * @param userKey    사용자 키
	 * @param categoryId 카테고리 ID
	 * @param body       퀴즈 업데이트 요청 정보
	 * @return CommonResponse 객체
	 */
	@PatchMapping("/category/{categoryId}/quiz")
	public CommonResponse<?> updateQuiz(
		@RequestAttribute("userKey") final String userKey,
		@PathVariable("categoryId") final Long categoryId,
		@RequestBody final QuizUpdateBody body) {
		final QuizUpdateParam.Request request = ControllerQuizMapper.toUpdateParam(userKey,
			categoryId,
			body.getQuizzes());
		final int updateCnt = quizService.update(request);
		return ResponseHandler.handle(HttpStatus.OK.value(), "퀴즈 업데이트 성공", updateCnt);
	}

}
