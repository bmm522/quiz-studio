package quiz.domain.category.mapper;

import quiz.domain.category.Category;
import quiz.service.userCategory.dto.S_UserCategorySaveReqeust;

public class CategoryMapper {

    public static Category toEntityWhenSave(S_UserCategorySaveReqeust dto) {
        return Category.builder()
            .categoryName(dto.getTitle())
            .categoryDescription(dto.getDescription())
            .build();
    }
}
