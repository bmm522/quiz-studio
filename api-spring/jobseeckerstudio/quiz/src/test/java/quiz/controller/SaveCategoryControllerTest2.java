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
import quiz.controller.category.dto.C_CategorySaveRequest;
import quiz.controller.dto.CommonResponse;
import quiz.global.exception.ExistCategorySaveException;
import quiz.global.exception.InvalidParameterFromDtoException;
import quiz.service.usercategory.UserCategoryService;
import quiz.service.usercategory.dto.S_UserCategorySaveResponse;

@ExtendWith(MockitoExtension.class)
@DisplayName("Save Category 테스트")
public class SaveCategoryControllerTest2 {

	private UserCategoryService userCategoryService;

	private CategoryController categoryController;

	@BeforeEach
	void init() {
		userCategoryService = mock(UserCategoryService.class);
		categoryController = new CategoryController(userCategoryService);
	}

	@Test
	@DisplayName("save 정상적인 요청")
	void saveCategoryTest() {

		C_CategorySaveRequest request = C_CategorySaveRequest.builder()
			.title("test")
			.description("testtest")
			.build();
		String userKey = "testUserKey";

		S_UserCategorySaveResponse response = S_UserCategorySaveResponse.builder()
			.userKey("testUserKey")
			.title("test")
			.description("testtest")
			.build();

		when(userCategoryService.save(any())).thenReturn(response);

		CommonResponse<?> result = categoryController.saveCategory(userKey, request);

		assertThat(result.getStatus()).isEqualTo(201);
		assertThat(result.getMsg()).isEqualTo("카테고리 저장 성공");
		assertThat(result.getData()).isEqualTo(response);
	}

	@Test
	@DisplayName("save 파라미터 title 안들어왔을때")
	void saveCategoryWhenNotHaveTitleParameterRequest() {
		UserCategoryService userCategoryService = mock(UserCategoryService.class);
		C_CategorySaveRequest request = C_CategorySaveRequest.builder()
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
		UserCategoryService userCategoryService = mock(UserCategoryService.class);
		C_CategorySaveRequest request = C_CategorySaveRequest.builder()
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
		UserCategoryService userCategoryService = mock(UserCategoryService.class);
		C_CategorySaveRequest request = C_CategorySaveRequest.builder()
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
		UserCategoryService userCategoryService = mock(UserCategoryService.class);
		C_CategorySaveRequest request = C_CategorySaveRequest.builder()
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
		UserCategoryService userCategoryService = mock(UserCategoryService.class);
		C_CategorySaveRequest request = C_CategorySaveRequest.builder()
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
