package quiz.service.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class S_CategorySaveResponse {

	private String userKey;

	private String title;

	private String description;

}
