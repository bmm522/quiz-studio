package quiz.domain.userCategory.repository;

import java.util.Collection;
import java.util.List;

import quiz.domain.userCategory.UserCategory;

public interface UserCategoryQueryRepository {
    List<UserCategory> findByUserKeyAndTitle(String userKey, String title);


}
