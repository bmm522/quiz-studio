package quiz.service.category.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import quiz.domain.userCategory.repository.dto.UserCategoryDto;

@AllArgsConstructor
@Builder
@Getter
public class S_CategoryGetResponse {

    private List<UserCategoryDto> categories;

}
