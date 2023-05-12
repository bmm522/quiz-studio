package quiz.domain.customCategory.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CustomCategoryDto {

    private long categoryId;
    private String title;

    private String description;

    private String userKey;
}
