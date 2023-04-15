package com.jobseeckerstudio.user.quiz.entity.category.repository;

import com.jobseeckerstudio.user.quiz.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
