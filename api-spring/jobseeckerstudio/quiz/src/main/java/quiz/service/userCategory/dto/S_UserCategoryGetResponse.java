package quiz.service.usercategory.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import quiz.repository.category.dto.CategoryQueryDto;

@AllArgsConstructor
@Builder
@Getter
public class S_UserCategoryGetResponse {

	private List<CategoryQueryDto> categories;

}
