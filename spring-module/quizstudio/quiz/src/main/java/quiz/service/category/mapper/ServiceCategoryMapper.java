package quiz.service.category.mapper;

import java.util.List;
import quiz.domain.category.Category;
import quiz.repository.category.dto.CategoryQueryDto;
import quiz.service.category.dto.CategoryGetCondition;
import quiz.service.category.dto.CategoryGetResponse;
import quiz.service.category.dto.CategorySaveParam;
import quiz.service.category.dto.CategoryUpdateParam;

public class ServiceCategoryMapper {

	public static synchronized CategorySaveParam.Response toSaveResponse(final Category entity) {
		return CategorySaveParam.Response.builder()
			.userKey(entity.getUserKey())
			.title(entity.getCategoryTitle())
			.description(entity.getCategoryDescription())
			.build();
	}

	public static synchronized CategoryGetResponse toGetResponse(
		final List<CategoryQueryDto> categories, final Long categoryTotalCount) {
		final int totalCount = categories.size();
		final int totalPage = (int) (Math.ceil((double) categoryTotalCount / (double) 10));
		return CategoryGetResponse.builder()
			.categories(categories)
			.totalCount(categoryTotalCount)
			.totalPage(totalPage)
			.build();
	}

	public static synchronized CategoryUpdateParam.Response toUpdateResponse(
		final Category category) {
		return CategoryUpdateParam.Response.builder()
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
