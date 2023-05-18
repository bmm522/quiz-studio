package quiz.service.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class S_CategoryUpdateRequest {

	private Long categoryId;

	private String userKey;

	private String updateTitle;

	private String updateDescription;
}
