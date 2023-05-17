package quiz.service.usercategory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class S_UserCategoryUpdateResponse {

	private String userKey;

	private String updateTitle;

	private String updateDescription;

}
