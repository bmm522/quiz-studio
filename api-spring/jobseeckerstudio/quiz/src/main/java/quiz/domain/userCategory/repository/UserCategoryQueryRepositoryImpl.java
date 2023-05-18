//package quiz.domain.userCategory.repository;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import java.util.List;
//import java.util.Optional;
//import javax.persistence.EntityManager;
//import quiz.domain.category.QCategory;
//import quiz.domain.userCategory.QUserCategory;
//import quiz.domain.userCategory.UserCategory;
//import quiz.repository.category.dto.CategoryQueryDto;
//import quiz.repository.category.mapper.R_UserCategoryMapper;
//
//public class UserCategoryQueryRepositoryImpl implements UserCategoryQueryRepository {
//
//	private final JPAQueryFactory queryFactory;
//	QUserCategory userCategory = QUserCategory.userCategory;
//	QCategory category = QCategory.category;
//
//	public UserCategoryQueryRepositoryImpl(EntityManager entityManager) {
//		this.queryFactory = new JPAQueryFactory(entityManager);
//	}
//
//	@Override
//	public Optional<UserCategory> findUserCategoryByUserKeyAndTitle(final String userKey,
//		final String title) {
//		return Optional.ofNullable(
//			queryFactory
//				.selectFrom(userCategory)
//				.join(category).on(userCategory.category.categoryId.eq(category.categoryId))
//				.where(userCategory.userKey.eq(userKey))
//				.where(category.categoryName.eq(title))
//				.fetchOne());
//	}
//
//	@Override
//	public List<CategoryQueryDto> findUserCategoryDtosByUserKey(final String userKey) {
//		List<UserCategory> userCategories =
//			queryFactory.selectFrom(userCategory)
//				.join(userCategory.category, category)
//				.where(userCategory.userKey.eq(userKey))
//				.fetch();
//		return R_UserCategoryMapper.toDto(userCategories);
//	}
//
//	@Override
//	public Optional<UserCategory> findUserCategoryByUserCategoryId(final Long userCategoryId) {
//		return Optional.ofNullable(
//			queryFactory.selectFrom(userCategory)
//				.join(userCategory.category, category)
//				.where(userCategory.userCategoryId.eq(userCategoryId))
//				.fetchOne());
//	}
//
//	@Override
//	public Optional<UserCategory> findUserCategoryByCategoryId(final long categoryId) {
//		return Optional.ofNullable(
//			queryFactory.selectFrom(userCategory)
//				.where(userCategory.category.categoryId.eq(categoryId))
//				.fetchOne());
//	}
//}
//
//
