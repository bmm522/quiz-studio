package quiz.repository.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import quiz.domain.category.Category;
import quiz.domain.category.QCategory;
import quiz.repository.category.dto.CategoryQueryDto;
import quiz.repository.category.mapper.CategoryQueryMapper;

public class CategoryQueryRepositoryImpl implements CategoryQueryRepository {

	private final JPAQueryFactory queryFactory;

	QCategory category;

	public CategoryQueryRepositoryImpl(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
		category = QCategory.category;
	}

	@Override
	public List<CategoryQueryDto> findCategoryDtosByUserKey(final String userKey, final int offset,
		final int pageSize) {
		final List<Category> categories = queryFactory
			.selectFrom(category)
			.where(category.userKey.eq(userKey))
			.offset(offset)
			.limit(pageSize)
			.fetch();
		return CategoryQueryMapper.toDto(categories);
	}

	@Override
	public Long getCategoryTotalCount(String userKey) {
		return queryFactory.select(category.count())
			.from(category)
			.where(category.userKey.eq(userKey))
			.fetchOne();
	}

	@Override
	public Category findCategoryByCategoryTitle(final String categoryTitle) {
		return queryFactory.selectFrom(category)
			.where(category.categoryTitle.eq(categoryTitle))
			.fetchOne();
	}

	@Override
	public Optional<Category> findCategoryByCategoryId(final Long categoryId) {
		return Optional.ofNullable(
			queryFactory
				.selectFrom(category)
				.where(category.id.eq(categoryId))
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
