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
import quiz.controller.quiz.dto.C_QuizSaveRequest;
import quiz.controller.quiz.mapper.C_QuizMapper;
import quiz.service.quiz.QuizService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class QuizController {

	private final QuizService quizService;

	@PostMapping("/category/{categoryId}/quiz")
	public CommonResponse<?> saveQuiz(@RequestAttribute("userKey") final String userKey,
		@RequestBody final C_QuizSaveRequest request,
		@PathVariable("categoryId") final long categoryId) {
		return ResponseHandler.handle(HttpStatus.CREATED.value(), "퀴즈 저장 성공",
			quizService.saveAll(C_QuizMapper.toSaveRequest(userKey, request, categoryId))
		);
	}

	@GetMapping("/category/{categoryId}/quiz")
	public CommonResponse<?> saveQuiz(@RequestAttribute("userKey") final String userKey,
		@PathVariable("categoryId") final long categoryId) {
		return ResponseHandler.handle(HttpStatus.CREATED.value(), "퀴즈 불러오기 성공",
			quizService.get(userKey, categoryId)
		);
	}
}
