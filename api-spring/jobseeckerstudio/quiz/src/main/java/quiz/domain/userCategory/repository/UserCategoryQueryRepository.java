package quiz.domain.userCategory.repository;

import java.util.List;
import java.util.Optional;
import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.dto.UserCategoryDto;

public interface UserCategoryQueryRepository {

	Optional<UserCategory> findUserCategoryByUserKeyAndTitle(final String userKey,
		final String title);

	List<UserCategoryDto> findUserCategoryDtosByUserKey(final String userKey);

	Optional<UserCategory> findUserCategoryByUserCategoryId(final Long userCategoryId);

	Optional<UserCategory> findUserCategoryByCategoryId(final long categoryId);
}
