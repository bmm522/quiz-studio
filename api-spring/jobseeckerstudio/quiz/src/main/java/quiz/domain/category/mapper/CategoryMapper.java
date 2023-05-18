package quiz.domain.category.mapper;

import quiz.domain.category.Category;
import quiz.service.usercategory.dto.S_UserCategorySaveRequest;

public class CategoryMapper {

	public static Category toEntityWhenSave(S_UserCategorySaveRequest dto) {
		return Category.builder()
			.categoryTitle(dto.getTitle())
			.categoryDescription(dto.getDescription())
			.userKey(dto.getUserKey())
			.build();
	}
}
