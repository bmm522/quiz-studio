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
import org.springframework.web.bind.annotation.RestController;
import quiz.controller.dto.CommonResponse;
import quiz.controller.dto.ResponseHandler;
import quiz.controller.quiz.dto.QuizSaveBody;
import quiz.controller.quiz.dto.QuizUpdateBody;
import quiz.controller.quiz.util.QuizConverter;
import quiz.service.quiz.QuizService;
import quiz.service.quiz.dto.QuizGetResponse;
import quiz.service.quiz.dto.QuizSaveParam;
import quiz.service.quiz.dto.QuizUpdateParam;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class QuizController {

	private final QuizService quizService;

	@PostMapping("/category/{categoryId}/quiz")
	public CommonResponse<?> saveQuiz(
		@RequestAttribute("userKey") final String userKey,
		@PathVariable("categoryId") final long categoryId,
		@RequestBody final QuizSaveBody body) {
		QuizSaveParam.Request request = QuizConverter.toSaveParam(
			userKey,
			categoryId,
			body.getQuizzes());
		QuizSaveParam.Response response = quizService.saveAll(request);

		return ResponseHandler.handle(HttpStatus.CREATED.value(), "퀴즈 저장 성공", response);
	}

	@GetMapping("/category/{categoryId}/quiz")
	public CommonResponse<?> saveQuiz(
		@RequestAttribute("userKey") final String userKey,
		@PathVariable("categoryId") final Long categoryId) {
		QuizGetResponse response = quizService.get(userKey, categoryId);

		return ResponseHandler.handle(HttpStatus.OK.value(), "퀴즈 불러오기 성공", response);
	}

	@PatchMapping("/category/{categoryId}/quiz")
	public CommonResponse<?> updateQuiz(
		@RequestAttribute("userKey") final String userKey,
		@PathVariable("categoryId") final Long categoryId,
		@RequestBody final QuizUpdateBody body) {
		QuizUpdateParam.Request request = QuizConverter.toUpdateParam(
			userKey,
			categoryId,
			body.getQuizzes()
		);
		int updateCnt = quizService.update(request);

		return ResponseHandler.handle(HttpStatus.OK.value(), "퀴즈 업데이트 성공", updateCnt);
	}
}
