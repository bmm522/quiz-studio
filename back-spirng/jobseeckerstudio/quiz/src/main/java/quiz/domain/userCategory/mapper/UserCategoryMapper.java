package quiz.domain.userCategory.mapper;

import quiz.domain.userCategory.UserCategory;
import quiz.service.category.dto.S_CategorySaveReqeust;

public class UserCategoryMapper {

    public static UserCategory toEntityWhenSave(S_CategorySaveReqeust reqeust) {
        return UserCategory.builder()
            .userKey(reqeust.getUserKey())
            .build();
    }
}
