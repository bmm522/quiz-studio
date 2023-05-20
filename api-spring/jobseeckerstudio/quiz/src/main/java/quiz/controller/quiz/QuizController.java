package quiz.controller.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quiz.controller.dto.CommonResponse;
import quiz.controller.dto.ResponseHandler;
import quiz.controller.quiz.dto.QuizSaveBody;
import quiz.controller.quiz.util.QuizConverter;
import quiz.service.quiz.QuizService;
import quiz.service.quiz.dto.QuizSaveParam;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class QuizController {

	private final QuizService quizService;

	@PostMapping("/category/{categoryId}/quiz")
	public CommonResponse<?> saveQuiz(
		@RequestAttribute("userKey") final String userKey,
		@RequestBody final QuizSaveBody body,
		@PathVariable("categoryId") final long categoryId) {

		QuizSaveParam.Request request = QuizConverter.toSaveParam(
			userKey,
			body.getQuizzes(),
			categoryId);
		QuizSaveParam.Response response = quizService.saveAll(request);

		return ResponseHandler.handle(HttpStatus.CREATED.value(), "퀴즈 저장 성공", response);
	}

	@GetMapping("/category/{categoryId}/quiz")
	public CommonResponse<?> saveQuiz(@RequestAttribute("userKey") final String userKey,
		@PathVariable("categoryId") final long categoryId) {
		return ResponseHandler.handle(HttpStatus.OK.value(), "퀴즈 불러오기 성공",
			quizService.get(userKey, categoryId)
		);
	}
}
