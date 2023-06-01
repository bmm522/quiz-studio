package com.quizbatch.domain.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;


public class CategoryQueryRepositoryImpl implements CategoryQueryRepository {


	private final JPAQueryFactory queryFactory;

	QCategory category;

	public CategoryQueryRepositoryImpl(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
		category = QCategory.category;
	}

	/**
	 * 카테고리 제목에 해당하는 카테고리를 조회하는 메서드입니다.
	 *
	 * @param categoryTitle 카테고리 제목
	 * @return 조회된 카테고리 객체 (존재하지 않을 경우 null)
	 */
	public Category findCategoryByCategoryTitle(final String categoryTitle) {
		return queryFactory.selectFrom(category)
			.where(category.categoryTitle.eq(categoryTitle))
			.fetchOne();
	}


}
