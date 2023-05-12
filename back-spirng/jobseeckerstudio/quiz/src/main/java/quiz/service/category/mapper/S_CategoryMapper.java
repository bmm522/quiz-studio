package quiz.service.category.mapper;

import java.util.List;

import quiz.domain.customCategory.CustomCategory;
import quiz.domain.customCategory.repository.dto.CustomCategoryDto;
import quiz.service.category.dto.S_CategoryGetResponse;
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

    public static S_CategoryGetResponse toGetResponse(List<CustomCategoryDto> customCategoryList) {
        return S_CategoryGetResponse.builder()
            .categories(customCategoryList)
            .build();
    }
}
