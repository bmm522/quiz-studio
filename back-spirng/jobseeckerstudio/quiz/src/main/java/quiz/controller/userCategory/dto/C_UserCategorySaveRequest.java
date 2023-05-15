package quiz.controller.userCategory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class C_UserCategorySaveRequest {

    private String title;
    private String description;

}
