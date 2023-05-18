package quiz.service.usercategory.mapper;

import java.util.List;
import quiz.domain.category.Category;
import quiz.repository.category.dto.CategoryQueryDto;
import quiz.service.usercategory.dto.S_UserCategoryGetResponse;
import quiz.service.usercategory.dto.S_UserCategorySaveResponse;
import quiz.service.usercategory.dto.S_UserCategoryUpdateResponse;

public class S_UserCategoryMapper {

	public static S_UserCategorySaveResponse toSaveResponse(Category entity) {
		return S_UserCategorySaveResponse.builder()
			.userKey(entity.getUserKey())
			.title(entity.getCategoryTitle())
			.description(entity.getCategoryDescription())
			.build();
	}

	public static S_UserCategoryGetResponse toGetResponse(
		List<CategoryQueryDto> categories) {
		return S_UserCategoryGetResponse.builder()
			.categories(categories)
			.build();
	}

	public static S_UserCategoryUpdateResponse toUpdateResponse(Category category) {
		return S_UserCategoryUpdateResponse.builder()
			.userKey(category.getUserKey())
			.updateTitle(category.getCategoryTitle())
			.updateDescription(category.getCategoryDescription())
			.build();
	}
}
