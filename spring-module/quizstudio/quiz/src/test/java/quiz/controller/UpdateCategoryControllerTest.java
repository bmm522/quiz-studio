package quiz.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import quiz.controller.category.CategoryController;
import quiz.controller.category.dto.CategoryUpdateBody;
import quiz.controller.dto.CommonResponse;
import quiz.global.exception.ExistCategorySaveException;
import quiz.service.category.CategoryService;
import quiz.service.category.dto.CategoryUpdateParam;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryControllerTest {

	private CategoryService categoryService;

	private CategoryController categoryController;

	@BeforeEach
	void init() {
		categoryService = mock(CategoryService.class);
		categoryController = new CategoryController(categoryService);
	}

	@Test
	@DisplayName("updateAllTitle 정상적인 요청")
	void updateCategoryTest() {
		CategoryUpdateBody request = CategoryUpdateBody.builder()
			.categoryId(1L)
			.updateTitle("updateTitle")
			.updateDescription("updateDescription")
			.build();
		String userKey = "testUserKey";

		CategoryUpdateParam.Response response = CategoryUpdateParam.Response.builder()
			.updateTitle("updateTitle")
			.updateDescription("updateDescription")
			.build();
		when(categoryService.update(any())).thenReturn(response);

		CommonResponse<?> result = categoryController.updateCategories(userKey, request);

		assertThat(result.getStatus()).isEqualTo(200);
		assertThat(result.getMsg()).isEqualTo("카테고리 업데이트 성공");
		assertThat(result.getData()).isEqualTo(response);
	}

	@Test
	@DisplayName("기존 카테고리 이름으로 요청을 보냈을 때 (자바)")
	void updateCategoryWhenExistCategoryWithJava() {
		CategoryUpdateBody request = CategoryUpdateBody.builder()
			.categoryId(1L)
			.updateTitle("java")
			.updateDescription("updateDescription")
			.build();
		String userKey = "testUserKey";
		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			categoryController.updateCategories(userKey, request);
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}

	@Test
	@DisplayName("기존 카테고리 이름으로 요청을 보냈을 때(자료구조)")
	void updateCategoryWhenExistCategoryWithDataStructure() {
		CategoryUpdateBody request = CategoryUpdateBody.builder()
			.categoryId(1L)
			.updateTitle("Data Structure")
			.updateDescription("updateDescription")
			.build();
		String userKey = "testUserKey";
		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			categoryController.updateCategories(userKey, request);
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}

	@Test
	@DisplayName("기존 카테고리 이름으로 요청을 보냈을 때(공백포함)")
	void updateCategoryWhenExistCategoryWithBlank() {
		CategoryUpdateBody request = CategoryUpdateBody.builder()
			.categoryId(1L)
			.updateTitle("  DAta Str Uct Ure   ")
			.updateDescription("updateDescription")
			.build();
		String userKey = "testUserKey";
		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			categoryController.updateCategories(userKey, request);
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}
}
