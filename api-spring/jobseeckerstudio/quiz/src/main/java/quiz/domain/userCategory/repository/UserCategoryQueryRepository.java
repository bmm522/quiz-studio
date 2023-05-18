//package quiz.domain.userCategory.repository;
//
//import java.util.List;
//import java.util.Optional;
//import quiz.domain.userCategory.UserCategory;
//import quiz.repository.category.dto.CategoryQueryDto;
//
//public interface UserCategoryQueryRepository {
//
//	Optional<UserCategory> findUserCategoryByUserKeyAndTitle(final String userKey,
//		final String title);
//
//	List<CategoryQueryDto> findUserCategoryDtosByUserKey(final String userKey);
//
//	Optional<UserCategory> findUserCategoryByUserCategoryId(final Long userCategoryId);
//
//	Optional<UserCategory> findUserCategoryByCategoryId(final long categoryId);
//}
