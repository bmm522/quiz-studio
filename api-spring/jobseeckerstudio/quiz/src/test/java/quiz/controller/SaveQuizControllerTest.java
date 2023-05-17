package quiz.controller;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import quiz.controller.quiz.QuizController;
import quiz.controller.quiz.dto.C_QuizSaveRequest;
import quiz.global.dto.CustomQuizDto;
import quiz.properties.JwtProperties;
import quiz.service.quiz.QuizService;
import quiz.service.quiz.dto.C_QuizSaveResponse;

@WebMvcTest(QuizController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc
public class SaveQuizControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private QuizService quizService;

	private ObjectMapper om;

	private String jwt;
	private HttpHeaders headers;

	@BeforeEach
	void init() {
		JwtProperties jwtProperties = new JwtProperties();
		jwtProperties.setSecret("asdfgffff112kdffj3532fgffffgsffsff");
		jwtProperties.setTokenPrefix("testTokenPrefix");
		jwtProperties.setHeaderJwt("testJwt");
		jwtProperties.setIss("testIss");
		jwtProperties.init();
		jwt = JwtProperties.TOKEN_PREFIX + JWT.create()
			.withSubject("testUser")
			.withIssuer(JwtProperties.ISS)
			.withExpiresAt(new Date(System.currentTimeMillis() + 10000000))
			.withClaim("userKey", "testUser")
			.sign(Algorithm.HMAC256(JwtProperties.SECRET));
		headers = new HttpHeaders();

		headers.set(JwtProperties.HEADER_JWT, jwt);
		headers.set(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
		om = new ObjectMapper();
	}

	@Test
	@DisplayName("save 정상적인 요청")
	void saveQuizTest() throws Exception {
		// Prepare test data
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

		C_QuizSaveResponse returnDto = C_QuizSaveResponse.builder()
			.quizzes(quizDtoList)
			.userKey("testUser")
			.categoryId(1000L)
			.build();

		C_QuizSaveRequest requestDto = C_QuizSaveRequest.builder()
			.quizzes(quizDtoList)
			.build();

		when(quizService.save(any())).thenReturn(returnDto);

		ResultActions perform = mockMvc.perform(
			post("/api/v1/category/1000/quiz")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers)
				.content(om.writeValueAsString(requestDto))
		);

		String charset = "UTF-8";
		byte[] responseBytes = perform.andDo(print()).andReturn().getResponse()
			.getContentAsByteArray();
		String body = new String(responseBytes, charset);

		DocumentContext dc = JsonPath.parse(body);
		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String userKey = dc.read("$.data.userKey");
		int categoryId = dc.read("$.data.categoryId");
		String quiz1Title = dc.read("$.data.quizzes[0].title");
		String quiz1Choice1Content = dc.read("$.data.quizzes[0].choices[0].content");
		boolean quiz1Choice1Answer = dc.read("$.data.quizzes[0].choices[0].answer");
		boolean quiz1Choice2Answer = dc.read("$.data.quizzes[0].choices[1].answer");

		assertThat(status).isEqualTo(201);
		assertThat(msg).isEqualTo("퀴즈 저장 성공");
		assertThat(userKey).isEqualTo("testUser");
		assertThat(categoryId).isEqualTo(1000);
		assertThat(quiz1Title).isEqualTo("예제 문제1");
		assertThat(quiz1Choice1Content).isEqualTo("예제 보기1");
		assertThat(quiz1Choice1Answer).isEqualTo(true);
		assertThat(quiz1Choice2Answer).isEqualTo(false);
	}
}
