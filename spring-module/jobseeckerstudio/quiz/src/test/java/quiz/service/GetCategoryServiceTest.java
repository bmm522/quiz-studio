package quiz.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quiz.repository.category.dto.CategoryQueryDto;
import quiz.service.category.dto.CategoryGetResponse;


public class GetCategoryServiceTest extends ServiceTest {


	@Test
	@DisplayName("서비스 단에서 카테고리 목록을 불러온다")
	void 서비스_단에서_카테고리_목록을_불러온다() {
		String userKey = "testUser";
		CategoryQueryDto dto1 = CategoryQueryDto.builder().userKey(userKey).title("title1")
			.description("description1").build();
		CategoryQueryDto dto2 = CategoryQueryDto.builder().userKey(userKey).title("title2")
			.description("description2").build();
		CategoryQueryDto dto3 = CategoryQueryDto.builder().userKey(userKey).title("title3")
			.description("description3").build();
		List<CategoryQueryDto> categoryQueryDtos = new ArrayList<>();
		categoryQueryDtos.add(dto1);
		categoryQueryDtos.add(dto2);
		categoryQueryDtos.add(dto3);

		when(categoryRepository.findCategoryDtosByUserKey(anyString(), anyInt(),
			anyInt())).thenReturn(
			categoryQueryDtos);

		CategoryGetResponse response = categoryService.get(userKey, 1);

		assertThat(response.getCategories().get(0).getUserKey()).isEqualTo(userKey);
		assertThat(response.getCategories().size()).isEqualTo(3);

	}


}
// @Test
// @DisplayName("정상적인 요청")
// void getTest() {
//     String userKey = "userKey1";
//     UserCategory category = UserCategory.builder()
//         .userKey(userKey)
//         .title("testTitle1")
//         .description("testDescription1")
//         .build();
//     UserCategory category2 = UserCategory.builder()
//         .userKey(userKey)
//         .title("testTitle2")
//         .description("testDescription1")
//         .build();
//     UserCategory category3 = UserCategory.builder()
//         .userKey(userKey)
//         .title("testTitle3")
//         .description("testDescription1")
//         .build();
//
//     List<UserCategory> userCategoryList = new ArrayList<>();
//     userCategoryList.add(category);
//     userCategoryList.add(category2);
//     userCategoryList.add(category3);
//
//     when(userCategoryRepository.findByUserKey(userKey)).thenReturn(userCategoryList);
//
//     CategoryGetResponse result = categoryService.getQuizzesWithPaging(userKey);
//     assertThat(result.getCategories().size()).isEqualTo(3);
// }
