package quiz.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import quiz.repository.category.dto.CategoryQueryDto;
import quiz.service.category.dto.CategoryGetResponse;

public class GetCategoryControllerTest extends ControllerTest {

	@BeforeEach
	void init() {
		setUp();
	}

	@Test
	@DisplayName("getQuizzesWithPaging 정상적인 요청")
	void 페이징이_된_퀴즈목록을_가져온다() throws Exception {
		String userKey = "testUser";
		CategoryQueryDto category = CategoryQueryDto.builder()
			.userKey(userKey)
			.title("testTitle1")
			.description("testDescription1")
			.build();
		CategoryQueryDto category2 = CategoryQueryDto.builder()
			.userKey(userKey)
			.title("testTitle2")
			.description("testDescription1")
			.build();
		CategoryQueryDto category3 = CategoryQueryDto.builder()
			.userKey(userKey)
			.title("testTitle3")
			.description("testDescription1")
			.build();

		List<CategoryQueryDto> customCategoryList = new ArrayList<>();
		customCategoryList.add(category);
		customCategoryList.add(category2);
		customCategoryList.add(category3);

		CategoryGetResponse responseFromService = CategoryGetResponse.builder()
			.categories(customCategoryList)
			.build();

		when(categoryService.get(any(String.class), any(Integer.class))).thenReturn(
			responseFromService);

		ResultActions perform = mockMvc.perform(
			get("/quiz-spring/api/v1/category?page=1")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers)
		);

		String body = decodeBody(perform);

		DocumentContext dc = JsonPath.parse(body);

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String title = dc.read("$.data.categories[0].title");

		assertThat(status).isEqualTo(200);
		assertThat(msg).isEqualTo("카테고리 불러오기 성공");
		assertThat(title).isEqualTo("testTitle1");

	}
}
