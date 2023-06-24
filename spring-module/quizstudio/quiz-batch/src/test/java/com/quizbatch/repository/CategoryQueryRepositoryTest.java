package com.quizbatch.repository;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.quizbatch.domain.category.Category;
import com.quizbatch.domain.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class CategoryQueryRepositoryTest {


	@Autowired
	private CategoryRepository categoryRepository;


	@Test
	void 카테고리_이름으로_카테고리값_가져오기_테스트() {
		Category category = Category.builder()
			.categoryTitle("testCategory")
			.categoryDescription("testCategoryDescription")
			.userKey("testUser")
			.build();

		Category savedCategory = categoryRepository.save(category);

		assertThat(savedCategory.getCategoryTitle()).isEqualTo("testCategory");
	}

}
