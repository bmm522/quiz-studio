package quiz.service.category.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import quiz.repository.category.dto.CategoryQueryDto;

@AllArgsConstructor
@Builder
@Getter
public class S_CategoryGetResponse {

	private List<CategoryQueryDto> categories;

	private Long totalCount;

	private int totalPage;

}
