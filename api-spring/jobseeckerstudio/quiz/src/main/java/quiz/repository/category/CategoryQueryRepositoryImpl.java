package quiz.repository.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import quiz.domain.category.Category;
import quiz.domain.category.QCategory;
import quiz.repository.category.dto.CategoryQueryDto;
import quiz.repository.category.mapper.R_UserCategoryMapper;

public class CategoryQueryRepositoryImpl implements CategoryQueryRepository {

	private final JPAQueryFactory queryFactory;

	QCategory category = QCategory.category;

	public CategoryQueryRepositoryImpl(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	@Override
	public List<CategoryQueryDto> findCategoryDtosByUserKey(final String userKey) {
		List<Category> categories = queryFactory
			.selectFrom(category)
			.where(category.userKey.eq(userKey)).fetch();
		return R_UserCategoryMapper.toDto(categories);
	}

	@Override
	public Optional<Category> findCategoryByCategoryId(final Long categoryId) {
		return Optional.ofNullable(
			queryFactory
				.selectFrom(category)
				.where(category.categoryId.eq(categoryId))
				.fetchOne()
		);
	}

	@Override
	public Optional<Category> findCategoryByUserKeyAndTitle(final String userKey,
		final String title) {
		return Optional.ofNullable(
			queryFactory
				.selectFrom(category)
				.where(category.userKey.eq(userKey)
					.and(category.categoryTitle.eq(title)))
				.fetchOne()
		);
	}

}
