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
import quiz.controller.category.CategoryController;
import quiz.controller.dto.CommonResponse;
import quiz.repository.category.dto.CategoryQueryDto;
import quiz.service.category.CategoryService;
import quiz.service.category.dto.S_CategoryGetResponse;

@ExtendWith(MockitoExtension.class)
@DisplayName("Get Category 테스트")
public class GetCategoryControllerTest2 {

	private CategoryService categoryService;

	private CategoryController categoryController;

	@BeforeEach
	void init() {
		categoryService = mock(CategoryService.class);
		categoryController = new CategoryController(categoryService);
	}

	@Test
	@DisplayName("getQuizzesWithPaging 정상적인 요청")
	void getCategoryTest() {
		String userKey = "testUser1";
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

		S_CategoryGetResponse responseFromService = S_CategoryGetResponse.builder()
			.categories(customCategoryList)
			.build();

		when(categoryService.get(any())).thenReturn(responseFromService);

		CommonResponse<?> result = categoryController.getCategories(userKey);

		assertThat(result.getStatus()).isEqualTo(200);
		assertThat(result.getMsg()).isEqualTo("카테고리 불러오기 성공");
		assertThat(result.getData()).isEqualTo(responseFromService);
	}
}
