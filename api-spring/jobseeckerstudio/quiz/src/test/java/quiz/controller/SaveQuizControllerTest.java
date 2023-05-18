package quiz.controller;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import quiz.controller.quiz.dto.C_QuizSaveRequest;
import quiz.global.dto.CustomQuizDto;
import quiz.service.quiz.dto.S_QuizSaveResponse;


public class SaveQuizControllerTest extends ControllerTest {

	List<CustomQuizDto> quizDtoList;

	@BeforeEach
	void init() {
		setUp();
		quizDtoList = new ArrayList<>();
		quizDtoList.add(
			CustomQuizDto.createQuiz("예제 문제1", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 1)
		);
		quizDtoList.add(
			CustomQuizDto.createQuiz("예제 문제2", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 2)
		);
		quizDtoList.add(
			CustomQuizDto.createQuiz("예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 3)
		);
	}


	@Test
	@DisplayName("save 정상적인 요청")
	void saveQuizTest() throws Exception {
		// Prepare test data
		S_QuizSaveResponse returnDto = S_QuizSaveResponse.builder()
			.quizzes(quizDtoList)
			.userKey("testUser")
			.categoryId(1000L)
			.build();

		C_QuizSaveRequest requestDto = C_QuizSaveRequest.builder()
			.quizzes(quizDtoList)
			.build();

		when(quizService.saveAll(any())).thenReturn(returnDto);
//		when(userCategoryRepository.saveAll(any())).thenReturn(returnDto);

		ResultActions perform = mockMvc.perform(
			post("/api/v1/category/1000/quiz")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers)
				.content(om.writeValueAsString(requestDto))
		);
		String body = decodeBody(perform);

		DocumentContext dc = JsonPath.parse(body);
		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String userKey = dc.read("$.data.userKey");
		int categoryId = dc.read("$.data.categoryId");
		String quiz1Title = dc.read("$.data.quizzes[0].title");
		String quiz1Choice1Content = dc.read("$.data.quizzes[0].choices[0].content");
		boolean quiz1Choice1Answer = dc.read("$.data.quizzes[0].choices[0].isAnswer");
		boolean quiz1Choice2Answer = dc.read("$.data.quizzes[0].choices[1].isAnswer");

		assertThat(status).isEqualTo(201);
		assertThat(msg).isEqualTo("퀴즈 저장 성공");
		assertThat(userKey).isEqualTo("testUser");
		assertThat(categoryId).isEqualTo(1000);
		assertThat(quiz1Title).isEqualTo("예제 문제1");
		assertThat(quiz1Choice1Content).isEqualTo("예제 보기1");
		assertThat(quiz1Choice1Answer).isEqualTo(true);
		assertThat(quiz1Choice2Answer).isEqualTo(false);
	}

	@Test
	@DisplayName("정답이 없는 데이터를 요청했을 때")
	void saveQuizTestWhenNotCorrectAnswer() throws Exception {
		quizDtoList.add(
			CustomQuizDto.createQuiz("예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 5)
		);

		S_QuizSaveResponse returnDto = S_QuizSaveResponse.builder()
			.quizzes(quizDtoList)
			.userKey("testUser")
			.categoryId(1000L)
			.build();

		C_QuizSaveRequest requestDto = C_QuizSaveRequest.builder()
			.quizzes(quizDtoList)
			.build();

		ResultActions perform = mockMvc.perform(
			post("/api/v1/category/1000/quiz")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers)
				.content(om.writeValueAsString(requestDto))
		);

		String body = decodeBody(perform);

		DocumentContext dc = JsonPath.parse(body);
		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String errorName = dc.read("$.errorName");

		assertThat(status).isEqualTo(400);
		assertThat(msg).isEqualTo("정확히 하나의 답만 선택해야 합니다. 퀴즈의 현재 선택된 답안 수 : 0");
		assertThat(errorName).isEqualTo("NotCorrectAnswerException");
	}
}
