package quiz.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import quiz.controller.dto.CommonResponse;
import quiz.controller.usercategory.UserCategoryController;
import quiz.domain.userCategory.repository.dto.UserCategoryDto;
import quiz.service.usercategory.UserCategoryService;
import quiz.service.usercategory.dto.S_UserCategoryGetResponse;

@ExtendWith(MockitoExtension.class)
@DisplayName("Get Category 테스트")
public class GetUserCategoryControllerTest {

	private UserCategoryService userCategoryService;

	private UserCategoryController userCategoryController;

	@BeforeEach
	void init() {
		userCategoryService = mock(UserCategoryService.class);
		userCategoryController = new UserCategoryController(userCategoryService);
	}

	@Test
	@DisplayName("get 정상적인 요청")
	void getCategoryTest() {
		String userKey = "testUser1";
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

		CommonResponse<?> result = userCategoryController.getCategories(userKey);

		assertThat(result.getStatus()).isEqualTo(200);
		assertThat(result.getMsg()).isEqualTo("카테고리 불러오기 성공");
		assertThat(result.getData()).isEqualTo(responseFromService);
	}
}
