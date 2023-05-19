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
import quiz.global.exception.DuplicateTitleException;
import quiz.service.category.dto.S_CategorySaveRequest;
import quiz.service.category.dto.S_CategorySaveResponse;

@ExtendWith(MockitoExtension.class)
public class SaveCategoryServiceTest extends ServiceTest {


	S_CategorySaveRequest reqeust;

	Category category;


	@BeforeEach
	void init() {
		reqeust = S_CategorySaveRequest.builder()
			.userKey("testUser")
			.title("testTitle")
			.description("testDescription")
			.build();

		category = Category.builder()
			.categoryTitle("testTitle")
			.categoryDescription("testDescription")
			.userKey("testUser")
			.build();

		setUp();
	}

	@Test
	@DisplayName("서비스 단에서 카테고리 하나를 저장한다")
	void 서비스_단에서_카테고리_하나를_저장한다() {
		// given
		when(categoryRepository.save(any())).thenReturn(category);
		// when
		S_CategorySaveResponse response = categoryService.save(reqeust);
		// then

		assertThat(response.getUserKey()).isEqualTo("testUser");
		assertThat(response.getDescription()).isEqualTo("testDescription");
	}

	@Test
	@DisplayName("카테고리 이름의 중복이 있는 경우")
	void 카테고리_이름의_중복이_있는_경우() {
		when(categoryRepository.findCategoryByUserKeyAndTitle(any(), any())).thenReturn(
			Optional.of(category));

		Exception exception = assertThrows(DuplicateTitleException.class, () -> {
			categoryService.save(reqeust);
		});

		assertThat(exception.getMessage()).isEqualTo("중복된 카테고리 제목은 사용할 수 없습니다.");
	}

}
