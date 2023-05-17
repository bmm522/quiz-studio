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
import quiz.controller.dto.CommonResponse;
import quiz.controller.usercategory.UserCategoryController;
import quiz.controller.usercategory.dto.C_UserCategoryUpdateRequest;
import quiz.global.exception.ExistCategorySaveException;
import quiz.service.usercategory.UserCategoryService;
import quiz.service.usercategory.dto.S_UserCategoryUpdateResponse;

@ExtendWith(MockitoExtension.class)
public class UpdateUserCategoryControllerTest {

	private UserCategoryService userCategoryService;

	private UserCategoryController userCategoryController;

	@BeforeEach
	void init() {
		userCategoryService = mock(UserCategoryService.class);
		userCategoryController = new UserCategoryController(userCategoryService);
	}

	@Test
	@DisplayName("update 정상적인 요청")
	void updateCategoryTest() {
		C_UserCategoryUpdateRequest request = C_UserCategoryUpdateRequest.builder()
			.userCategoryId(1L)
			.updateTitle("updateTitle")
			.updateDescription("updateDescription")
			.build();
		String userKey = "testUserKey";

		S_UserCategoryUpdateResponse response = S_UserCategoryUpdateResponse.builder()
			.updateTitle("updateTitle")
			.updateDescription("updateDescription")
			.build();
		when(userCategoryService.update(any())).thenReturn(response);

		CommonResponse<?> result = userCategoryController.updateCategories(userKey, request);

		assertThat(result.getStatus()).isEqualTo(200);
		assertThat(result.getMsg()).isEqualTo("카테고리 업데이트 성공");
		assertThat(result.getData()).isEqualTo(response);
	}

	@Test
	@DisplayName("기존 카테고리 이름으로 요청을 보냈을 때 (자바)")
	void updateCategoryWhenExistCategoryWithJava() {
		C_UserCategoryUpdateRequest request = C_UserCategoryUpdateRequest.builder()
			.userCategoryId(1L)
			.updateTitle("java")
			.updateDescription("updateDescription")
			.build();
		String userKey = "testUserKey";
		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			userCategoryController.updateCategories(userKey, request);
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}

	@Test
	@DisplayName("기존 카테고리 이름으로 요청을 보냈을 때(자료구조)")
	void updateCategoryWhenExistCategoryWithDataStructure() {
		C_UserCategoryUpdateRequest request = C_UserCategoryUpdateRequest.builder()
			.userCategoryId(1L)
			.updateTitle("Data Structure")
			.updateDescription("updateDescription")
			.build();
		String userKey = "testUserKey";
		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			userCategoryController.updateCategories(userKey, request);
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}

	@Test
	@DisplayName("기존 카테고리 이름으로 요청을 보냈을 때(공백포함)")
	void updateCategoryWhenExistCategoryWithBlank() {
		C_UserCategoryUpdateRequest request = C_UserCategoryUpdateRequest.builder()
			.userCategoryId(1L)
			.updateTitle("  DAta Str Uct Ure   ")
			.updateDescription("updateDescription")
			.build();
		String userKey = "testUserKey";
		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			userCategoryController.updateCategories(userKey, request);
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}
}
