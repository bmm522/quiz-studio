package quiz.service.category.mapper;

import java.util.List;

import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.dto.UserCategoryDto;
import quiz.service.category.dto.S_CategoryGetResponse;
import quiz.service.category.dto.S_CategorySaveResponse;

public class S_CategoryMapper {
    public static S_CategorySaveResponse toSaveResponse(UserCategory entity) {
        return S_CategorySaveResponse.builder()
            .userKey(entity.getUserKey())
            .title(entity.getCategoryName())
            .description(entity.getCategoryDescription())
            .build();
    }

    public static S_CategoryGetResponse toGetResponse(List<UserCategoryDto> customCategoryList) {
        return S_CategoryGetResponse.builder()
            .categories(customCategoryList)
            .build();
    }
}
