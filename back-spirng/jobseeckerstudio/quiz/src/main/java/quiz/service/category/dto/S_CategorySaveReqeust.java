package quiz.service.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class S_CategorySaveReqeust {

    private String userKey;

    private String title;

    private String description;

}
