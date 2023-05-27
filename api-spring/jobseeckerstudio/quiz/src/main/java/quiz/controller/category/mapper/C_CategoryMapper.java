package quiz.controller.category.mapper;

import quiz.controller.category.dto.C_CategorySaveRequest;
import quiz.controller.category.dto.C_CategoryUpdateRequest;
import quiz.global.exception.ExistCategorySaveException;
import quiz.global.exception.InvalidParameterFromDtoException;
import quiz.service.category.dto.S_CategorySaveRequest;
import quiz.service.category.dto.S_CategoryUpdateRequest;

public class C_CategoryMapper {

	public static S_CategorySaveRequest toSaveRequest(final String userKey,
		final C_CategorySaveRequest request) {
		validateTitleAndDescription(request.getTitle(), request.getDescription());
		checkExistCategoryName(request.getTitle());
		return S_CategorySaveRequest.builder()
			.userKey(userKey)
			.title(request.getTitle())
			.description(request.getDescription())
			.build();
	}

	public static S_CategoryUpdateRequest toUpdateReqeust(final String userKey,
		final C_CategoryUpdateRequest request) {
		checkExistCategoryName(request.getUpdateTitle());
		return S_CategoryUpdateRequest.builder()
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
