package quiz.service.userCategory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class S_UserCategoryUpdateRequest {

    private Long userCategoryId;

    private String userKey;

    private String updateTitle;

    private String updateDescription;
}
