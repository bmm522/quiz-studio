package quiz.controller.category.mapper;

import quiz.controller.category.dto.C_CategorySaveRequest;
import quiz.controller.category.dto.C_CategoryUpdateRequest;
import quiz.global.exception.ExistCategorySaveException;
import quiz.global.exception.InvalidParameterFromDtoException;
import quiz.service.usercategory.dto.S_UserCategorySaveRequest;
import quiz.service.usercategory.dto.S_UserCategoryUpdateRequest;

public class C_CategoryMapper {

	public static S_UserCategorySaveRequest toSaveRequest(final String userKey,
		final C_CategorySaveRequest request) {
		validateTitleAndDescription(request.getTitle(), request.getDescription());
		checkExistCategoryName(request.getTitle());
		return S_UserCategorySaveRequest.builder()
			.userKey(userKey)
			.title(request.getTitle())
			.description(request.getDescription())
			.build();
	}

	public static S_UserCategoryUpdateRequest toUpdateReqeust(final String userKey,
		final C_CategoryUpdateRequest request) {
		checkExistCategoryName(request.getUpdateTitle());
		return S_UserCategoryUpdateRequest.builder()
			.categoryId(request.getUserCategoryId())
			.userKey(userKey)
			.updateTitle(request.getUpdateTitle())
			.updateDescription(request.getUpdateDescription())
			.build();
	}

	private static void validateTitleAndDescription(String title, String description) {
		if (title == null || title.isEmpty()) {
			throw new InvalidParameterFromDtoException("save 요청에 title이 담기지 않았습니다.");
		}

		if (description == null || description.isEmpty()) {
			throw new InvalidParameterFromDtoException("save 요청에 description이 담기지 않았습니다.");
		}

	}

	private static void checkExistCategoryName(String title) {
		String checkStr = title.toLowerCase().trim().replace(" ", "");

		if ("java".equals(checkStr) || "datastructure".equals(checkStr)) {
			throw new ExistCategorySaveException("기존의 카테고리 이름은 사용할 수 없습니다.");
		}
	}

}
