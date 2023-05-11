package quiz.service.category.mapper;

import quiz.domain.customCategory.CustomCategory;
import quiz.service.category.dto.S_CategorySaveReqeust;
import quiz.service.category.dto.S_CategorySaveResponse;

public class S_CategoryMapper {
    public static S_CategorySaveResponse toSaveResponse(CustomCategory entity) {
        return S_CategorySaveResponse.builder()
            .userKey(entity.getUserKey())
            .title(entity.getTitle())
            .description(entity.getDescription())
            .build();
    }

    // public static toSaveRequest(S_CategorySaveReqeust reqeust) {
    //
    // }
}
