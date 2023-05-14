package quiz.domain.userCategory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import quiz.domain.userCategory.UserCategory;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long>, UserCategoryQueryRepository {
    // List<UserCategory> findByUserKeyAndTitle(String userKey, String title);

    // List<UserCategory> findByUserKey(String userKey);
}
