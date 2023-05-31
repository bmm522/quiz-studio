package quiz.controller.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CategoryUpdateBody {

	private Long categoryId;

	private String updateTitle;

	private String updateDescription;

}
