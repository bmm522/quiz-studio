package com.quizbatch.domain.category;


public interface CategoryQueryRepository {

	Category findCategoryByCategoryTitle(final String categoryTitle);

}
