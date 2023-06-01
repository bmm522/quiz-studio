package quiz.domain.category.mapper;

import quiz.domain.category.Category;
import quiz.service.category.dto.CategorySaveParam;

public class CategoryMapper {

	/**
	 * 카테고리 저장 요청 데이터를 엔티티로 변환합니다.
	 *
	 * @param dto 카테고리 저장 요청 데이터
	 * @return 카테고리 엔티티
	 */
	public static Category toEntityWhenSave(final CategorySaveParam.Request dto) {
		return Category.builder()
			.categoryTitle(dto.getTitle())
			.categoryDescription(dto.getDescription())
			.userKey(dto.getUserKey())
			.build();
	}
}
