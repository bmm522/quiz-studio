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
import quiz.domain.userCategory.repository.dto.UserCategoryDto;
import quiz.service.usercategory.dto.S_UserCategoryGetResponse;

public class GetUserCategoryControllerTest extends ControllerTest {

	@BeforeEach
	void init() {
		setUp();
	}

	@Test
	@DisplayName("get 정상적인 요청")
	void getCategoryTest() throws Exception {
		String userKey = "testUser";
		UserCategoryDto category = UserCategoryDto.builder()
			.userKey(userKey)
			.title("testTitle1")
			.description("testDescription1")
			.build();
		UserCategoryDto category2 = UserCategoryDto.builder()
			.userKey(userKey)
			.title("testTitle2")
			.description("testDescription1")
			.build();
		UserCategoryDto category3 = UserCategoryDto.builder()
			.userKey(userKey)
			.title("testTitle3")
			.description("testDescription1")
			.build();

		List<UserCategoryDto> customCategoryList = new ArrayList<>();
		customCategoryList.add(category);
		customCategoryList.add(category2);
		customCategoryList.add(category3);

		S_UserCategoryGetResponse responseFromService = S_UserCategoryGetResponse.builder()
			.categories(customCategoryList)
			.build();

		when(userCategoryService.get(any())).thenReturn(responseFromService);

		ResultActions perform = mockMvc.perform(
			get("/api/v1/category")
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
