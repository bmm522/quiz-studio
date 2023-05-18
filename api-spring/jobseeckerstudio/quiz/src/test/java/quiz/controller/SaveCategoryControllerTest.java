package quiz.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import quiz.controller.category.dto.C_CategorySaveRequest;
import quiz.service.category.dto.S_CategorySaveResponse;

public class SaveCategoryControllerTest extends ControllerTest {

	@BeforeEach
	void init() {
		setUp();
	}

	@Test
	@DisplayName("save 정상적인 요청")
	void saveCategoryTest() throws Exception {
		C_CategorySaveRequest request = C_CategorySaveRequest.builder()
			.title("test")
			.description("testtest")
			.build();

		S_CategorySaveResponse response = S_CategorySaveResponse.builder()
			.userKey("testUser")
			.title("test")
			.description("testtest")
			.build();

		when(categoryService.save(any())).thenReturn(response);

		ResultActions perform = mockMvc.perform(
			post("/api/v1/category")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers)
				.content(om.writeValueAsString(request))
		);

		String body = decodeBody(perform);

		DocumentContext dc = JsonPath.parse(body);

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String userKey = dc.read("$.data.userKey");
		String title = dc.read("$.data.title");
		String description = dc.read("$.data.description");

		assertThat(status).isEqualTo(201);
		assertThat(msg).isEqualTo("카테고리 저장 성공");
		assertThat(userKey).isEqualTo("testUser");
		assertThat(title).isEqualTo("test");
		assertThat(description).isEqualTo("testtest");
	}

	@Test
	@DisplayName("save 파라미터 title 안들어왔을때")
	void saveCategoryWhenNotHaveTitleParameterRequest() throws Exception {
		C_CategorySaveRequest request = C_CategorySaveRequest.builder()
			.build();

		ResultActions perform = mockMvc.perform(
			post("/api/v1/category")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers)
				.content(om.writeValueAsString(request))
		);

		String body = decodeBody(perform);

		DocumentContext dc = JsonPath.parse(body);

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String errorName = dc.read("$.errorName");

		assertThat(status).isEqualTo(400);
		assertThat(msg).isEqualTo("save 요청에 title이 담기지 않았습니다.");
		assertThat(errorName).isEqualTo("InvalidParameterFromDtoException");
	}

	@Test
	@DisplayName("save 파라미터 description 안들어왔을때")
	void saveCategoryWhenNotHaveDescriptionParameterRequest() throws Exception {
		C_CategorySaveRequest request = C_CategorySaveRequest.builder()
			.title("testTitle")
			.build();

		ResultActions perform = mockMvc.perform(
			post("/api/v1/category")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers)
				.content(om.writeValueAsString(request))
		);

		String body = decodeBody(perform);

		DocumentContext dc = JsonPath.parse(body);

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String errorName = dc.read("$.errorName");

		assertThat(status).isEqualTo(400);
		assertThat(msg).isEqualTo("save 요청에 description이 담기지 않았습니다.");
		assertThat(errorName).isEqualTo("InvalidParameterFromDtoException");
	}

	@Test
	@DisplayName("기존 카테고리 이름으로 요청을 보냈을 때 (자바)")
	void saveCategoryWhenExistCategoryWithJava() throws Exception {
		C_CategorySaveRequest request = C_CategorySaveRequest.builder()
			.title("java")
			.description("testDescription")
			.build();
		ResultActions perform = mockMvc.perform(
			post("/api/v1/category")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers)
				.content(om.writeValueAsString(request))
		);

		String body = decodeBody(perform);

		DocumentContext dc = JsonPath.parse(body);

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String errorName = dc.read("$.errorName");

		assertThat(status).isEqualTo(400);
		assertThat(msg).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
		assertThat(errorName).isEqualTo("ExistCategorySaveException");
	}

	@Test
	@DisplayName("기존 카테고리 이름으로 요청을 보냈을 때(자료구조)")
	void saveCategoryWhenExistCategoryWithDataStructure() throws Exception {
		C_CategorySaveRequest request = C_CategorySaveRequest.builder()
			.title("Data Structure")
			.description("testDescription")
			.build();

		ResultActions perform = mockMvc.perform(
			post("/api/v1/category")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers)
				.content(om.writeValueAsString(request))
		);

		String body = decodeBody(perform);

		DocumentContext dc = JsonPath.parse(body);

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String errorName = dc.read("$.errorName");

		assertThat(status).isEqualTo(400);
		assertThat(msg).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
		assertThat(errorName).isEqualTo("ExistCategorySaveException");
	}

	@Test
	@DisplayName("기존 카테고리 이름으로 요청을 보냈을 때(공백포함)")
	void saveCategoryWhenExistCategoryWithBlank() throws Exception {
		C_CategorySaveRequest request = C_CategorySaveRequest.builder()
			.title("  DAta Str Uct Ure   ")
			.description("testDescription")
			.build();

		ResultActions perform = mockMvc.perform(
			post("/api/v1/category")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers)
				.content(om.writeValueAsString(request))
		);

		String body = decodeBody(perform);

		DocumentContext dc = JsonPath.parse(body);

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String errorName = dc.read("$.errorName");

		assertThat(status).isEqualTo(400);
		assertThat(msg).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
		assertThat(errorName).isEqualTo("ExistCategorySaveException");
	}
}
