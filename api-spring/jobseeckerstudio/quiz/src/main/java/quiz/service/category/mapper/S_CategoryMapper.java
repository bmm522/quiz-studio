package quiz.service.category.mapper;

import java.util.List;
import quiz.domain.category.Category;
import quiz.repository.category.dto.CategoryQueryDto;
import quiz.service.category.dto.CategoryGetCondition;
import quiz.service.category.dto.S_CategoryGetResponse;
import quiz.service.category.dto.S_CategorySaveResponse;
import quiz.service.category.dto.S_CategoryUpdateResponse;

public class S_CategoryMapper {

	public static synchronized S_CategorySaveResponse toSaveResponse(final Category entity) {
		return S_CategorySaveResponse.builder()
			.userKey(entity.getUserKey())
			.title(entity.getCategoryTitle())
			.description(entity.getCategoryDescription())
			.build();
	}

	public static synchronized S_CategoryGetResponse toGetResponse(
		final List<CategoryQueryDto> categories, final Long categoryTotalCount) {
		final int totalCount = categories.size();
		final int totalPage = (int) (Math.ceil((double) categoryTotalCount / (double) 10));
		return S_CategoryGetResponse.builder()
			.categories(categories)
			.totalCount(categoryTotalCount)
			.totalPage(totalPage)
			.build();
	}

	public static synchronized S_CategoryUpdateResponse toUpdateResponse(final Category category) {
		return S_CategoryUpdateResponse.builder()
			.userKey(category.getUserKey())
			.updateTitle(category.getCategoryTitle())
			.updateDescription(category.getCategoryDescription())
			.build();
	}

	public static synchronized CategoryGetCondition toGetCondition(String userKey, int page) {
		return CategoryGetCondition.builder()
			.userKey(userKey)
			.page(page)
			.build();
	}
}
