package quiz.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import quiz.domain.category.Category;
import quiz.global.exception.NotFoundEntityException;
import quiz.global.exception.PermissionException;
import quiz.service.category.dto.CategoryUpdateParam;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryServiceTest extends ServiceTest {

	Category category;

	CategoryUpdateParam.Request request;

	@BeforeEach
	void init() {

		category = Category.builder()
			.id(1L)
			.categoryTitle("testTitle")
			.categoryDescription("testDescription")
			.userKey("testUser")
			.build();

		request = CategoryUpdateParam.Request.builder()
			.categoryId(1L)
			.updateTitle("updateTitle")
			.updateDescription("updateDescription")
			.userKey("testUser")
			.build();

		setUp();
	}

	@Test
	@DisplayName("서비스 단에서 카테고리 업데이트")
	void 서비스_단에서_카테고리_업데이트() {

		when(categoryRepository.findCategoryByCategoryId(any())).thenReturn(
			Optional.of(category));

		CategoryUpdateParam.Response response = categoryService.update(request);

		assertThat(response.getUpdateTitle()).isEqualTo("updateTitle");
		assertThat(response.getUpdateDescription()).isEqualTo("updateDescription");
		assertThat(response.getUserKey()).isEqualTo("testUser");
	}

	@Test
	@DisplayName("유저키로부터 찾는 카테고리 객체가 null 일때")
	void 유저키로부터_찾는_카테고리_객체가_null_일때() {

		when(categoryRepository.findCategoryByCategoryId(any())).thenReturn(
			Optional.empty());

		Exception exception = assertThrows(NotFoundEntityException.class, () -> {
			categoryService.update(request);
		});

		assertThat(exception.getMessage()).isEqualTo("userKey로 해당 Category 객체를 찾을 수 없습니다.");
	}

	@Test
	@DisplayName("request 유저키와 디비에 저장된 카테고리의 유저키가 맞지않을 때")
	void request_유저키와_디비에_저장된_카테고리의_유저키가_맞지않을_때() {
		Category category2 = Category.builder().userKey("testUser2").build();
		when(categoryRepository.findCategoryByCategoryId(any())).thenReturn(
			Optional.of(category2));

		Exception exception = assertThrows(PermissionException.class, () -> {
			categoryService.update(request);
		});

		assertThat(exception.getMessage()).isEqualTo("권한이 없는 회원입니다. (userKey 일치하지 않음)");
	}

}
