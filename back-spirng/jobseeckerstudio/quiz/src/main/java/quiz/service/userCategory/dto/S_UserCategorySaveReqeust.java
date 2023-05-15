package quiz.service.userCategory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class S_UserCategorySaveReqeust {

    private String userKey;

    private String title;

    private String description;

}
