package quiz.domain.customCategory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import quiz.domain.customCategory.CustomCategory;

public interface CustomCategoryRepository extends JpaRepository<CustomCategory, Long> {
    List<CustomCategory> findByUserKeyAndTitle(String userKey, String title);

    List<CustomCategory> findByUserKey(String userKey);
}
