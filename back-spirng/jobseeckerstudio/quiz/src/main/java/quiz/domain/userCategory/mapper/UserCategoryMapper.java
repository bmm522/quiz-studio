package quiz.domain.userCategory.mapper;

import quiz.domain.userCategory.UserCategory;
import quiz.service.userCategory.dto.S_UserCategorySaveReqeust;

public class UserCategoryMapper {

    public static UserCategory toEntityWhenSave(S_UserCategorySaveReqeust reqeust) {
        return UserCategory.builder()
            .userKey(reqeust.getUserKey())
            .build();
    }
}
