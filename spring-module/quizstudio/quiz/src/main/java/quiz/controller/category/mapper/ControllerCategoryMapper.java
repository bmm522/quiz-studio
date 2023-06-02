package quiz.controller.category.mapper;

import quiz.controller.category.dto.CategorySaveBody;
import quiz.controller.category.dto.CategoryUpdateBody;
import quiz.global.exception.ExistCategorySaveException;
import quiz.global.exception.InvalidParameterFromDtoException;
import quiz.service.category.dto.CategorySaveParam;
import quiz.service.category.dto.CategoryUpdateParam;

/**
 * 해당 클래스는 컨트롤러의 DTO를 서비스의 DTO로 변환해주는 기능을 제공합니다.
 */
public class ControllerCategoryMapper {

	/**
	 * CategorySaveParam.Request 객체를 생성하여 반환하는 메서드입니다.
	 *
	 * @param userKey 사용자 키
	 * @param request 카테고리 저장 요청 정보
	 * @return CategorySaveParam.Request 객체
	 * @throws InvalidParameterFromDtoException save 요청의 title이나 description이 유효하지 않을 때 예외 발생
	 * @throws ExistCategorySaveException       중복된 카테고리 이름이 있을 때 예외 발생
	 */
	public static CategorySaveParam.Request toSaveRequest(final String userKey,
		final CategorySaveBody request) {
		validateTitleAndDescription(request.getTitle(), request.getDescription()); // 제목과 설명 유효성 검사
		checkExistCategoryName(request.getTitle()); // 중복 카테고리 이름 검사
		return CategorySaveParam.Request.builder()
			.userKey(userKey)
			.title(request.getTitle())
			.description(request.getDescription())
			.build();
	}

	/**
	 * CategoryUpdateParam.Request 객체를 생성하여 반환하는 메서드입니다.
	 *
	 * @param userKey 사용자 키
	 * @param request 카테고리 업데이트 요청 정보
	 * @return CategoryUpdateParam.Request 객체
	 * @throws ExistCategorySaveException 중복된 카테고리 이름이 있을 때 예외 발생
	 */
	public static CategoryUpdateParam.Request toUpdateRequest(final String userKey,
		final CategoryUpdateBody request) {
		checkExistCategoryName(request.getUpdateTitle()); // 중복 카테고리 이름 검사
		return CategoryUpdateParam.Request.builder()
			.categoryId(request.getCategoryId())
			.userKey(userKey)
			.updateTitle(request.getUpdateTitle())
			.updateDescription(request.getUpdateDescription())
			.build();
	}

	/**
	 * 제목과 설명이 유효한지 검사하는 메서드입니다.
	 *
	 * @param title       제목
	 * @param description 설명
	 * @throws InvalidParameterFromDtoException save 요청의 title이나 description이 유효하지 않을 때 예외 발생
	 */
	private static void validateTitleAndDescription(final String title, final String description) {
		if (title == null || title.isEmpty()) {
			throw new InvalidParameterFromDtoException("save 요청에 title이 담기지 않았습니다.");
		}

		if (description == null || description.isEmpty()) {
			throw new InvalidParameterFromDtoException("save 요청에 description이 담기지 않았습니다.");
		}
	}

	/**
	 * 카테고리 이름이 중복되는지 검사하는 메서드입니다.
	 *
	 * @param title 카테고리 제목
	 * @throws ExistCategorySaveException 기존의 카테고리 이름과 중복될 때 예외 발생
	 */
	private static void checkExistCategoryName(final String title) {
		String checkStr = title.toLowerCase().trim().replace(" ", "");

		if ("java".equals(checkStr) || "datastructure".equals(checkStr) || "database".equals(
			checkStr)) {
			throw new ExistCategorySaveException("기존의 카테고리 이름은 사용할 수 없습니다.");
		}
	}
}
