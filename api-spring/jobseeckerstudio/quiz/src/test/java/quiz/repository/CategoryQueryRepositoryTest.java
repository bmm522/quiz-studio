package quiz.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import quiz.domain.category.Category;
import quiz.domain.category.repository.CategoryRepository;
import quiz.repository.category.dto.CategoryQueryDto;

@DataJpaTest
public class CategoryQueryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@BeforeEach
	void init() {
		Category category = Category.builder()
			.categoryTitle("testCategoryName")
			.categoryDescription("testCategoryDescription")
			.userKey("testUser")
			.build();

		Category category2 = Category.builder()
			.categoryTitle("testCategoryName2")
			.categoryDescription("testCategoryDescription2")
			.userKey("testUser")
			.build();

		categoryRepository.save(category);
		categoryRepository.save(category2);
	}

	@Test
	@DisplayName("유저키와 제목으로 카테고리 하나 찾아오기")
	void 유저키와_제목으로_카테고리_하나_찾아오기() {
		Category category = categoryRepository.findCategoryByUserKeyAndTitle(
			"testUser", "testCategoryName").get();

		assertThat(category.getUserKey()).isEqualTo("testUser");
		assertThat(category.getCategoryTitle()).isEqualTo("testCategoryName");
		assertThat(category.getCategoryDescription()).isEqualTo("testCategoryDescription");
	}

	@Test
	@DisplayName("유저키로 해당 카테고리 목록 가져온 후 카테고리 쿼리 DTO로 반환")
	void 유저키로_해당_카테고리_목록_가져온_후_카테고리_쿼리_DTO로_반환() {
		List<CategoryQueryDto> categoryQueryDtos = categoryRepository.findCategoryDtosByUserKey(
			"testUser");
		assertThat(categoryQueryDtos.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("카테고리 아이디로 카테고리 하나 가져오기 ")
	void 카테고리_아이디로_카테고리_하나_가져오기() {
		Category category3 = Category.builder()
			.categoryTitle("testCategoryName3")
			.categoryDescription("testCategoryDescription3")
			.userKey("testUser")
			.build();

		Category savedData = categoryRepository.save(category3);
		Category userCategory = categoryRepository.findCategoryByCategoryId(
			savedData.getCategoryId()).get();

		assertThat(userCategory.getCategoryTitle()).isEqualTo("testCategoryName3");

	}
}
