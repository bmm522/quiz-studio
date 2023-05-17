package quiz.service.usercategory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class S_UserCategorySaveRequest {

	private String userKey;

	private String title;

	private String description;

}
