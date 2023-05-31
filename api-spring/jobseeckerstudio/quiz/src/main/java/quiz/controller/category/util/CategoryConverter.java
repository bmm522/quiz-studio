package quiz.controller.category.util;

import quiz.controller.category.dto.CategorySaveBody;
import quiz.controller.category.dto.CategoryUpdateBody;
import quiz.global.exception.ExistCategorySaveException;
import quiz.global.exception.InvalidParameterFromDtoException;
import quiz.service.category.dto.CategorySaveParam;
import quiz.service.category.dto.CategoryUpdateParam;

public class CategoryConverter {

	public static CategorySaveParam.Request toSaveRequest(final String userKey,
		final CategorySaveBody request) {
		validateTitleAndDescription(request.getTitle(), request.getDescription());
		checkExistCategoryName(request.getTitle());
		return CategorySaveParam.Request.builder()
			.userKey(userKey)
			.title(request.getTitle())
			.description(request.getDescription())
			.build();
	}

	public static CategoryUpdateParam.Request toUpdateReqeust(final String userKey,
		final CategoryUpdateBody request) {
		checkExistCategoryName(request.getUpdateTitle());
		return CategoryUpdateParam.Request.builder()
			.categoryId(request.getCategoryId())
			.userKey(userKey)
			.updateTitle(request.getUpdateTitle())
			.updateDescription(request.getUpdateDescription())
			.build();
	}

	private static void validateTitleAndDescription(final String title, final String description) {
		if (title == null || title.isEmpty()) {
			throw new InvalidParameterFromDtoException("save 요청에 title이 담기지 않았습니다.");
		}

		if (description == null || description.isEmpty()) {
			throw new InvalidParameterFromDtoException("save 요청에 description이 담기지 않았습니다.");
		}

	}

	private static void checkExistCategoryName(final String title) {
		String checkStr = title.toLowerCase().trim().replace(" ", "");

		if ("java".equals(checkStr) || "datastructure".equals(checkStr)) {
			throw new ExistCategorySaveException("기존의 카테고리 이름은 사용할 수 없습니다.");
		}
	}

}
