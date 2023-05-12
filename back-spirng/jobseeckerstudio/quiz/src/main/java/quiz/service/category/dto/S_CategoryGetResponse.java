package quiz.service.category.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import quiz.domain.customCategory.CustomCategory;
import quiz.domain.customCategory.repository.dto.CustomCategoryDto;

@AllArgsConstructor
@Builder
@Getter
public class S_CategoryGetResponse {

    private List<CustomCategoryDto> categories;

}
