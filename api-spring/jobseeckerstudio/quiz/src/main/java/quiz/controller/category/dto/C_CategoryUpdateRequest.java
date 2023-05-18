package quiz.controller.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class C_CategoryUpdateRequest {

	private Long userCategoryId;

	private String updateTitle;

	private String updateDescription;

}
