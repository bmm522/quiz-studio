package quiz.repository.category.mapper;

import java.util.List;
import java.util.stream.Collectors;
import quiz.domain.category.Category;
import quiz.repository.category.dto.CategoryQueryDto;

public class CategoryQueryMapper {

	public static List<CategoryQueryDto> toDto(List<Category> categories) {
		return categories.stream()
			.map(category -> CategoryQueryDto.builder()
				.categoryId(category.getId())
				.userKey(category.getUserKey())
				.title(category.getCategoryTitle())
				.description(category.getCategoryDescription())
				.build())
			.collect(Collectors.toList());
	}
}
