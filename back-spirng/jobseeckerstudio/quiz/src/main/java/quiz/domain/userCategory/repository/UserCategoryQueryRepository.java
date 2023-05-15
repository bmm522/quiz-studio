package quiz.domain.userCategory.repository;

import java.util.Collection;
import java.util.List;

import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.dto.UserCategoryDto;

public interface UserCategoryQueryRepository {
    List<UserCategory> findByUserKeyAndTitle(String userKey, String title);

    List<UserCategoryDto> findByUserKey(String userKey);
}
