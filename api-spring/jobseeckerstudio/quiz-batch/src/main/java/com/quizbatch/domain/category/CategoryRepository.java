package com.quizbatch.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import quiz.domain.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryQueryRepository {

}
