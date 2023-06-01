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

	public Category findCategoryByCategoryTitle(final String categoryTitle) {
		return queryFactory.selectFrom(category)
			.where(category.categoryTitle.eq(categoryTitle))
			.fetchOne();
	}


}
