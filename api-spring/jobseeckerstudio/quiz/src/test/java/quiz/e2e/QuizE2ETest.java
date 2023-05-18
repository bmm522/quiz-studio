package quiz.e2e;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import quiz.controller.quiz.dto.C_QuizSaveRequest;
import quiz.domain.category.Category;
import quiz.global.dto.CustomQuizDto;

public class QuizE2ETest extends E2ETest {

	private final String url = "/api/v1";

	private long categoryId;

	@BeforeAll
	void init() {
		setUp();
		category = Category.builder()
			.categoryTitle(testCategoryName)
			.categoryDescription(testCategoryDescription)
			.userKey(testUserKey)
			.build();

		categoryId = categoryRepository.save(category).getCategoryId();
	}

	@AfterAll
	void deleteData() {
		categoryRepository.deleteByUserKey(testUserKey);
	}

	@Test
	@DisplayName("퀴즈 저장")
	void 퀴즈_저장() throws JsonProcessingException {

		List<CustomQuizDto> quizDtoList = new ArrayList<>();
		quizDtoList.add(
			CustomQuizDto.createQuiz("예제 문제1", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 1)
		);
		quizDtoList.add(
			CustomQuizDto.createQuiz("예제 문제2", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 2)
		);
		quizDtoList.add(
			CustomQuizDto.createQuiz("예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 3)
		);

		C_QuizSaveRequest requestBody = C_QuizSaveRequest.builder()
			.quizzes(quizDtoList)
			.build();

		String body = om.writeValueAsString(requestBody);
		HttpEntity<String> request = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = rt.exchange(url + "/category/" + categoryId + "/quiz",
			HttpMethod.POST, request, String.class);

	}

}
