package quiz.service.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class S_CategoryUpdateResponse {

	private String userKey;

	private String updateTitle;

	private String updateDescription;

}
