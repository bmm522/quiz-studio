package quiz.domain.customCategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import quiz.domain.customCategory.CustomCategory;

public interface CustomCategoryRepository extends JpaRepository<CustomCategory, Long> {
}
