package com.quizbatch.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryQueryRepository {

	Category findByCategoryTitle(String categoryTitle);
}
