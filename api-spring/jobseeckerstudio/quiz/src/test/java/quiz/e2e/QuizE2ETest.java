package quiz.e2e;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import quiz.controller.quiz.dto.QuizSaveBody;
import quiz.domain.category.Category;
import quiz.global.dto.CustomQuizDto;

public class QuizE2ETest extends E2ETest {

	String url = "/api/v1";

	@BeforeAll
	void init() {
		setUp();
		category = Category.builder()
			.categoryTitle(testCategoryName)
			.categoryDescription(testCategoryDescription)
			.userKey(testUserKey)
			.build();

		long categoryId = categoryRepository.save(category).getId();
		url = "/api/v1/category/" + categoryId + "/quiz";
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
			CustomQuizDto.createForTest("예제 문제1", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 1)
		);
		quizDtoList.add(
			CustomQuizDto.createForTest("예제 문제2", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 2)
		);
		quizDtoList.add(
			CustomQuizDto.createForTest("예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 3)
		);

		QuizSaveBody requestBody = QuizSaveBody.builder()
			.quizzes(quizDtoList)
			.build();

		String body = om.writeValueAsString(requestBody);
		HttpEntity<String> request = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = rt.exchange(url,
			HttpMethod.POST, request, String.class);

		DocumentContext dc = JsonPath.parse(response.getBody());

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");

		assertThat(status).isEqualTo(201);
		assertThat(msg).isEqualTo("퀴즈 저장 성공");
	}

	@Test
	@DisplayName("정답이 없는 문제를 요청할 때 에러를 반환한다.")
	void 정답이_없는_문제를_요청할_때_에러를_반환한다() throws JsonProcessingException {

		List<CustomQuizDto> quizDtoList = new ArrayList<>();
		quizDtoList.add(
			CustomQuizDto.createForTest("예제 문제1", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 1)
		);
		quizDtoList.add(
			CustomQuizDto.createForTest("예제 문제2", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 2)
		);
		quizDtoList.add(
			CustomQuizDto.createForTest("예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 3)
		);
		quizDtoList.add(
			CustomQuizDto.createForTest("예제 문제4", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 5)
		);

//		QuizSaveBody requestBody = QuizSaveBody.builder()
//			.quizzes(quizDtoList)
//			.build();
//
		QuizSaveTestBody requestBody = QuizSaveTestBody.builder()
			.quizzes(quizDtoList)
			.build();

		String body = om.writeValueAsString(requestBody);
		HttpEntity<String> request = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = rt.exchange(url,
			HttpMethod.POST, request, String.class);

		DocumentContext dc = JsonPath.parse(response.getBody());

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String errorName = dc.read("$.errorName");
		assertThat(status).isEqualTo(400);
		assertThat(msg).isEqualTo("정확히 하나의 답만 선택해야 합니다. 퀴즈의 현재 선택된 답안 수 : 0");
		assertThat(errorName).isEqualTo("NotCorrectAnswerException");
	}

	@Test
	@DisplayName("퀴즈 불러오기")
	@Sql("classpath:db/e2eTestData.sql")
	void 퀴즈_불러오기() {
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<String> response = rt.exchange(url,
			HttpMethod.GET, request, String.class);

		DocumentContext dc = JsonPath.parse(response.getBody());

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");

		assertThat(status).isEqualTo(200);
		assertThat(msg).isEqualTo("퀴즈 불러오기 성공");
	}

}
