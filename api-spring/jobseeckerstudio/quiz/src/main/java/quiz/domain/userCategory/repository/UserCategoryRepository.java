package quiz.domain.userCategory.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import quiz.domain.userCategory.UserCategory;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long>, UserCategoryQueryRepository {
	@Transactional
    void deleteByUserKey(String saveTestUser);
	// List<UserCategory> findByUserKeyAndTitle(String userKey, String title);

    // List<UserCategory> findByUserKey(String userKey);
}
