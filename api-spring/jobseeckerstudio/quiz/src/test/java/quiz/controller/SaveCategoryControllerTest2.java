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
import quiz.controller.category.dto.CategorySaveBody;
import quiz.controller.dto.CommonResponse;
import quiz.global.exception.ExistCategorySaveException;
import quiz.global.exception.InvalidParameterFromDtoException;
import quiz.service.category.CategoryService;
import quiz.service.category.dto.S_CategorySaveResponse;

@ExtendWith(MockitoExtension.class)
@DisplayName("Save Category 테스트")
public class SaveCategoryControllerTest2 {

	private CategoryService categoryService;

	private CategoryController categoryController;

	@BeforeEach
	void init() {
		categoryService = mock(CategoryService.class);
		categoryController = new CategoryController(categoryService);
	}

	@Test
	@DisplayName("save 정상적인 요청")
	void saveCategoryTest() {

		CategorySaveBody request = CategorySaveBody.builder()
			.title("test")
			.description("testtest")
			.build();
		String userKey = "testUserKey";

		S_CategorySaveResponse response = S_CategorySaveResponse.builder()
			.userKey("testUserKey")
			.title("test")
			.description("testtest")
			.build();

		when(categoryService.save(any())).thenReturn(response);

		CommonResponse<?> result = categoryController.saveCategory(userKey, request);

		assertThat(result.getStatus()).isEqualTo(201);
		assertThat(result.getMsg()).isEqualTo("카테고리 저장 성공");
		assertThat(result.getData()).isEqualTo(response);
	}

	@Test
	@DisplayName("save 파라미터 title 안들어왔을때")
	void saveCategoryWhenNotHaveTitleParameterRequest() {
		CategoryService categoryService = mock(CategoryService.class);
		CategorySaveBody request = CategorySaveBody.builder()
			.build();
		String userKey = "testUserKey";

		Exception exception = assertThrows(InvalidParameterFromDtoException.class, () -> {
			categoryController.saveCategory(userKey, request);
		});

		assertThat(exception.getMessage()).isEqualTo("save 요청에 title이 담기지 않았습니다.");
	}

	@Test
	@DisplayName("save 파라미터 description 안들어왔을때")
	void saveCategoryWhenNotHaveDescriptionParameterRequest() {
		CategoryService categoryService = mock(CategoryService.class);
		CategorySaveBody request = CategorySaveBody.builder()
			.title("testTitle")
			.build();
		String userKey = "testUserKey";

		Exception exception = assertThrows(InvalidParameterFromDtoException.class, () -> {
			categoryController.saveCategory(userKey, request);
		});

		assertThat(exception.getMessage()).isEqualTo("save 요청에 description이 담기지 않았습니다.");
	}

	@Test
	@DisplayName("기존 카테고리 이름으로 요청을 보냈을 때 (자바)")
	void saveCategoryWhenExistCategoryWithJava() {
		CategoryService categoryService = mock(CategoryService.class);
		CategorySaveBody request = CategorySaveBody.builder()
			.title("java")
			.description("testDescription")
			.build();
		String userKey = "testUserKey";

		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			categoryController.saveCategory(userKey, request);
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}

	@Test
	@DisplayName("기존 카테고리 이름으로 요청을 보냈을 때(자료구조)")
	void saveCategoryWhenExistCategoryWithDataStructure() {
		CategoryService categoryService = mock(CategoryService.class);
		CategorySaveBody request = CategorySaveBody.builder()
			.title("Data Structure")
			.description("testDescription")
			.build();
		String userKey = "testUserKey";

		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			categoryController.saveCategory(userKey, request);
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}

	@Test
	@DisplayName("기존 카테고리 이름으로 요청을 보냈을 때(공백포함)")
	void saveCategoryWhenExistCategoryWithBlank() {
		CategoryService categoryService = mock(CategoryService.class);
		CategorySaveBody request = CategorySaveBody.builder()
			.title("  DAta Str Uct Ure   ")
			.description("testDescription")
			.build();
		String userKey = "testUserKey";

		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			categoryController.saveCategory(userKey, request);
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}

}
