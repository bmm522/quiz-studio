package quiz.service.category.mapper;

import java.util.List;
import quiz.domain.category.Category;
import quiz.repository.category.dto.CategoryQueryDto;
import quiz.service.category.dto.CategoryGetCondition;
import quiz.service.category.dto.CategoryGetResponse;
import quiz.service.category.dto.CategorySaveParam;
import quiz.service.category.dto.CategoryUpdateParam;

public class ServiceCategoryMapper {

	/**
	 * 엔티티 객체를 저장 응답 DTO로 변환합니다.
	 *
	 * @param entity 카테고리 엔티티
	 * @return 저장 응답 DTO
	 */
	public static CategorySaveParam.Response toSaveResponse(final Category entity) {
		return CategorySaveParam.Response.builder()
			.userKey(entity.getUserKey())
			.title(entity.getCategoryTitle())
			.description(entity.getCategoryDescription())
			.build();
	}

	/**
	 * 카테고리 조회 응답 DTO를 생성합니다.
	 *
	 * @param categories         카테고리 쿼리 DTO 목록
	 * @param categoryTotalCount 카테고리 총 개수
	 * @return 카테고리 조회 응답 DTO
	 */
	public static CategoryGetResponse toGetResponse(
		final List<CategoryQueryDto> categories, final Long categoryTotalCount) {
		final int totalCount = categories.size();
		final int totalPage = (int) (Math.ceil((double) categoryTotalCount / (double) 10));
		return CategoryGetResponse.builder()
			.categories(categories)
			.totalCount(categoryTotalCount)
			.totalPage(totalPage)
			.build();
	}

	/**
	 * 카테고리 업데이트 응답 DTO를 생성합니다.
	 *
	 * @param category 카테고리 엔티티
	 * @return 업데이트 응답 DTO
	 */
	public static CategoryUpdateParam.Response toUpdateResponse(
		final Category category) {
		return CategoryUpdateParam.Response.builder()
			.userKey(category.getUserKey())
			.updateTitle(category.getCategoryTitle())
			.updateDescription(category.getCategoryDescription())
			.build();
	}

	/**
	 * 카테고리 조회 조건 객체를 생성합니다.
	 *
	 * @param userKey 사용자 키
	 * @param page    페이지 번호
	 * @return 카테고리 조회 조건 객체
	 */
	public static CategoryGetCondition toGetCondition(String userKey, int page) {
		return CategoryGetCondition.builder()
			.userKey(userKey)
			.page(page)
			.build();
	}
}
