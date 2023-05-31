package com.quizbatch.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import quiz.domain.category.Category;
import quiz.domain.category.repository.CategoryRepository;

@SpringBootTest
@ComponentScan(basePackages = {"quiz.config", "quiz.repository", "quiz.domain"})
public class CategoryQueryRepositoryTest {


	@Autowired
	private CategoryRepository categoryRepository;

//	@Test
//	void CATEGORY_불러오는지_테스트() {
//
//		List<Category> categories = categoryRepository.findAll();
//		System.out.println(categories.size());
//	}

	@Test
	void 카테고리_이름으로_카테고리값_가져오기_테스트() {
		Category category = Category.builder()
			.categoryTitle("java")
			.build();
		Category category1 = categoryRepository.save(category);
		System.out.println(category1.getCategoryTitle());
		Category savedCategory = categoryRepository.findCategoryByCategoryTitle(
			category1.getCategoryTitle());
		System.out.println(savedCategory.getCategoryTitle());
	}

}
