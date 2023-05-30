package com.quizbatch.domain.category;

import quiz.domain.category.Category;

public interface CategoryQueryRepository {

	Category findCategoryByCategoryTitle(final String categoryTitle);

}
