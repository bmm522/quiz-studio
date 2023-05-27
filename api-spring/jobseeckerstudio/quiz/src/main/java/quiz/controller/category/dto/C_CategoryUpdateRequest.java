package quiz.controller.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class C_CategoryUpdateRequest {

	private Long categoryId;

	private String updateTitle;

	private String updateDescription;

}
