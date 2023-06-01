package quiz.controller.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CategorySaveBody {

	private String title;
	private String description;

}
