package quiz.controller.userCategory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class C_UserCategoryUpdateRequest {

    private Long userCategoryId;

    private String updateTitle;

    private String updateDescription;

}