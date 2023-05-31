package com.quizbatch.domain.category;

import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryQueryRepositoryImpl implements CategoryQueryRepository {


	private final EntityManager entityManager;

	public Category findCategoryByCategoryTitle(final String categoryTitle) {
		return entityManager.createQuery(
				"SELECT c FROM Category c WHERE c.categoryTitle = :categoryTitle", Category.class)
			.setParameter("categoryTitle", categoryTitle)
			.getSingleResult();
	}


}
