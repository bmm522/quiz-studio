package quiz.repository.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CategoryQueryDto {

	private long categoryId;
	private String title;

	private String description;

	private String userKey;
}
