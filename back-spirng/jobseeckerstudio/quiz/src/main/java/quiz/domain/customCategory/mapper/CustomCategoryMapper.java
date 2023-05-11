package quiz.domain.customCategory.mapper;

import quiz.domain.customCategory.CustomCategory;
import quiz.service.category.dto.S_CategorySaveReqeust;

public class CustomCategoryMapper {

    public static CustomCategory toEntityFromSaveRequest(S_CategorySaveReqeust reqeust) {
        return CustomCategory.builder()
            .userKey(reqeust.getUserKey())
            .title(reqeust.getTitle())
            .description(reqeust.getDescription())
            .build();
    }
}
