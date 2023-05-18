package quiz.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import quiz.domain.category.Category;
import quiz.domain.category.repository.CategoryRepository;
import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.UserCategoryRepository;
import quiz.global.exception.NotFoundEntityException;
import quiz.global.exception.PermissionException;
import quiz.service.usercategory.UserCategoryService;
import quiz.service.usercategory.dto.S_UserCategoryUpdateRequest;
import quiz.service.usercategory.dto.S_UserCategoryUpdateResponse;

@ExtendWith(MockitoExtension.class)
public class UpdateUserCategoryServiceTest {

	UserCategoryService userCategoryService;
	UserCategoryRepository userCategoryRepository;

	CategoryRepository categoryRepository;

	UserCategory userCategory;

	Category category;

	S_UserCategoryUpdateRequest request;

	@BeforeEach
	void init() {
		userCategoryRepository = mock(UserCategoryRepository.class);
		userCategoryService = new UserCategoryService(userCategoryRepository, categoryRepository);

		category = Category.builder().categoryId(1L).categoryName("testTitle")
			.categoryDescription("testDescription").userKey("testUser").build();

		userCategory = UserCategory.builder().userCategoryId(1L).userKey("testUser")
			.category(category).build();

		request = S_UserCategoryUpdateRequest.builder()
			.userCategoryId(1L)
			.updateTitle("updateTitle")
			.updateDescription("updateDescription")
			.userKey("testUser")
			.build();
	}

	@Test
	@DisplayName("정상적인 요청")
	void updateTest() {

		when(userCategoryRepository.findUserCategoryByUserCategoryId(any())).thenReturn(
			Optional.of(userCategory));

		S_UserCategoryUpdateResponse response = userCategoryService.update(request);

		assertThat(response.getUpdateTitle()).isEqualTo("updateTitle");
		assertThat(response.getUpdateDescription()).isEqualTo("updateDescription");
		assertThat(response.getUserKey()).isEqualTo("testUser");
	}

	@Test
	@DisplayName("userKey로부터 찾는 userCategory 객체가 null일때")
	void whenNullUserCategoryFromFindUserCategoryByUserKey() {

		when(userCategoryRepository.findUserCategoryByUserCategoryId(any())).thenReturn(
			Optional.empty());

		Exception exception = assertThrows(NotFoundEntityException.class, () -> {
			userCategoryService.update(request);
		});

		assertThat(exception.getMessage()).isEqualTo("userKey로 해당 UserCategory 객체를 찾을 수 없습니다.");
	}

	@Test
	@DisplayName("request userKey와 디비의 userKey가 맞지않을 때")
	void whenPermissionTest() {
		UserCategory userCategory2 = UserCategory.builder().userCategoryId(1L).userKey("testUser2")
			.category(category).build();
		when(userCategoryRepository.findUserCategoryByUserCategoryId(any())).thenReturn(
			Optional.of(userCategory2));

		Exception exception = assertThrows(PermissionException.class, () -> {
			userCategoryService.update(request);
		});

		assertThat(exception.getMessage()).isEqualTo("권한이 없는 회원입니다. (userKey 일치하지 않음)");
	}

}
