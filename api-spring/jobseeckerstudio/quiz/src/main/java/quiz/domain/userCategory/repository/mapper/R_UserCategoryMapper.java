package quiz.domain.userCategory.repository.mapper;

import java.util.List;
import java.util.stream.Collectors;

import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.dto.UserCategoryDto;

public class R_UserCategoryMapper {
    public static List<UserCategoryDto> toDto(List<UserCategory> userCategories) {
        return userCategories.stream()
            .map(userCategory -> UserCategoryDto.builder().categoryId(userCategory.getUserCategoryId())
                .userKey(userCategory.getUserKey())
                .title(userCategory.getCategoryName())
                .description(userCategory.getCategoryDescription())
                .build())
            .collect(Collectors.toList());
    }
}
