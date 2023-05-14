package quiz.domain.category.mapper;

import quiz.domain.category.Category;
import quiz.service.category.dto.S_CategorySaveReqeust;

public class CategoryMapper {

    public static Category toEntityWhenSave(S_CategorySaveReqeust dto) {
        return Category.builder()
            .categoryName(dto.getTitle())
            .categoryDescription(dto.getDescription())
            .build();
    }
}
