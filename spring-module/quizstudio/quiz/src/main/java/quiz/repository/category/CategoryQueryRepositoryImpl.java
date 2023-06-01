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

	private final QCategory category;

	public CategoryQueryRepositoryImpl(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
		this.category = QCategory.category;
	}

	/**
	 * 사용자 키에 해당하는 카테고리 DTO 목록을 조회하는 메서드입니다.
	 *
	 * @param userKey  사용자 키
	 * @param offset   조회 시작 위치
	 * @param pageSize 페이지 크기
	 * @return 카테고리 DTO 목록
	 */
	@Override
	public List<CategoryQueryDto> findCategoryDtosByUserKey(final String userKey, final int offset,
		int pageSize) {
		List<Category> categories = queryFactory
			.selectFrom(category)
			.where(category.userKey.eq(userKey))
			.offset(offset)
			.limit(pageSize)
			.fetch();
		return CategoryQueryMapper.toDto(categories);
	}

	/**
	 * 사용자 키에 해당하는 카테고리 옵션용 DTO 목록을 조회하는 메서드입니다.
	 *
	 * @param userKey 사용자 키
	 * @return 카테고리 옵션용 DTO 목록
	 */
	@Override
	public List<CategoryQueryDto> findCategoryDtosByUserKeyWhenSelectOption(final String userKey) {
		List<Category> categories = queryFactory
			.selectFrom(category)
			.where(category.userKey.eq(userKey))
			.fetch();
		return CategoryQueryMapper.toDto(categories);
	}

	/**
	 * 사용자 키에 해당하는 카테고리의 총 개수를 조회하는 메서드입니다.
	 *
	 * @param userKey 사용자 키
	 * @return 카테고리의 총 개수
	 */
	@Override
	public Long getCategoryTotalCount(final String userKey) {
		return queryFactory
			.select(category.count())
			.from(category)
			.where(category.userKey.eq(userKey))
			.fetchOne();
	}

	/**
	 * 카테고리 제목에 해당하는 카테고리를 조회하는 메서드입니다.
	 *
	 * @param categoryTitle 카테고리 제목
	 * @return 조회된 카테고리 객체
	 */
	@Override
	public Category findCategoryByCategoryTitle(final String categoryTitle) {
		return queryFactory
			.selectFrom(category)
			.where(category.categoryTitle.eq(categoryTitle))
			.fetchOne();
	}

	/**
	 * 카테고리 ID에 해당하는 카테고리를 조회하는 메서드입니다.
	 *
	 * @param categoryId 카테고리 ID
	 * @return 조회된 카테고리 객체 (존재하지 않을 경우 Optional.empty())
	 */
	@Override
	public Optional<Category> findCategoryByCategoryId(final Long categoryId) {
		return Optional.ofNullable(
			queryFactory
				.selectFrom(category)
				.where(category.id.eq(categoryId))
				.fetchOne()
		);
	}

	/**
	 * 사용자 키와 카테고리 제목에 해당하는 카테고리를 조회하는 메서드입니다.
	 *
	 * @param userKey 사용자 키
	 * @param title   카테고리 제목
	 * @return 조회된 카테고리 객체 (존재하지 않을 경우 Optional.empty())
	 */
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
