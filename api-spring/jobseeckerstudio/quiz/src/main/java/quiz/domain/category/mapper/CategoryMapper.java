package quiz.domain.category.mapper;

import quiz.domain.category.Category;
import quiz.service.userCategory.dto.S_UserCategorySaveRequest;

public class CategoryMapper {

    public static Category toEntityWhenSave(S_UserCategorySaveRequest dto) {
        return Category.builder()
            .categoryName(dto.getTitle())
            .categoryDescription(dto.getDescription())
            .build();
    }
}
