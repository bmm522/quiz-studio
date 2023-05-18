package quiz.domain.category.mapper;

import quiz.domain.category.Category;
import quiz.service.category.dto.S_CategorySaveRequest;

public class CategoryMapper {

	public static Category toEntityWhenSave(S_CategorySaveRequest dto) {
		return Category.builder()
			.categoryTitle(dto.getTitle())
			.categoryDescription(dto.getDescription())
			.userKey(dto.getUserKey())
			.build();
	}
}
