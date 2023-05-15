package quiz.service.userCategory.mapper;

import java.util.List;

import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.dto.UserCategoryDto;
import quiz.service.userCategory.dto.S_UserCategoryGetResponse;
import quiz.service.userCategory.dto.S_UserCategorySaveResponse;

public class S_UserCategoryMapper {
    public static S_UserCategorySaveResponse toSaveResponse(UserCategory entity) {
        return S_UserCategorySaveResponse.builder()
            .userKey(entity.getUserKey())
            .title(entity.getCategoryName())
            .description(entity.getCategoryDescription())
            .build();
    }

    public static S_UserCategoryGetResponse toGetResponse(List<UserCategoryDto> customCategoryList) {
        return S_UserCategoryGetResponse.builder()
            .categories(customCategoryList)
            .build();
    }
}
