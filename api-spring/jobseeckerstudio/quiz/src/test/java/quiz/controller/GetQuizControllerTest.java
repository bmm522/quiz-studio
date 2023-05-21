package quiz.controller;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import quiz.domain.category.Category;
import quiz.repository.quiz.dto.QuizQueryDto;
import quiz.service.quiz.dto.QuizGetResponse;

public class GetQuizControllerTest extends ControllerTest {

	Category category;

	protected final String testUserKey = "testUserKey";
	protected final String testCategoryName = "testCategoryName";

	protected final String testCategoryDescription = "testCategoryDescription";

	@BeforeEach
	void init() {
		setUp();
	}

	@Test
	@DisplayName("퀴즈 불러오기")
	void 퀴즈_불러오기() throws Exception {
		List<QuizQueryDto> quizQeuryDtoList = List.of(
			QuizQueryDto.createForTest("예제 카테고리", "예제 문제1", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4",
				1),
			QuizQueryDto.createForTest("예제 카테고리", "예제 문제2", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4",
				2),
			QuizQueryDto.createForTest("예제 카테고리", "예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4",
				3),
			QuizQueryDto.createForTest("예제 카테고리", "예제 문제4", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4",
				4)
		);

		QuizGetResponse returnDto = QuizGetResponse.builder().quizzes(quizQeuryDtoList).build();

		when(quizService.get(anyString(), anyLong())).thenReturn(returnDto);

		ResultActions perform = mockMvc.perform(
			get("/api/v1/category/1000/quiz")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers)
		);

		String body = decodeBody(perform);
		DocumentContext dc = JsonPath.parse(body);
		System.out.println(body);
		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String checkQuizTitle = dc.read("$.data.quizzes[0].quizTitle");
		String checkQuizChoice = dc.read("$.data.quizzes[0].choices[0].choiceContent");

		assertThat(status).isEqualTo(200);
		assertThat(msg).isEqualTo("퀴즈 불러오기 성공");
		assertThat(checkQuizTitle).isEqualTo("예제 문제1");
		assertThat(checkQuizChoice).isEqualTo("예제 보기1");
	}

}
