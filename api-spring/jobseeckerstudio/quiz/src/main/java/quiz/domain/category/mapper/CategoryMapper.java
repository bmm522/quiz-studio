package quiz.domain.category.mapper;

import quiz.domain.category.Category;
import quiz.service.category.dto.CategorySaveParam;

public class CategoryMapper {

	public static Category toEntityWhenSave(CategorySaveParam.Request dto) {
		return Category.builder()
			.categoryTitle(dto.getTitle())
			.categoryDescription(dto.getDescription())
			.userKey(dto.getUserKey())
			.build();
	}
}
