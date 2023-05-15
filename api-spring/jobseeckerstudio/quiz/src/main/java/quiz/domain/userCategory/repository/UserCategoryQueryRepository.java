package quiz.domain.userCategory.repository;

import java.util.List;
import java.util.Optional;

import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.dto.UserCategoryDto;

public interface UserCategoryQueryRepository {
    Optional<UserCategory> findUserCategoryByUserKeyAndTitle(String userKey, String title);

    List<UserCategoryDto> findUserCategoryDtosByUserKey(String userKey);

    Optional<UserCategory> findUserCategoryByUserCategoryId(Long userCategoryId);
}
