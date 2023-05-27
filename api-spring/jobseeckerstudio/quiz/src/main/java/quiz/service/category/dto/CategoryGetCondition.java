package quiz.service.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CategoryGetCondition {

	private String userKey;

	private int page;

	private final int pageSize = 10;

	public int getOffset() {
		return (this.page - 1) * pageSize;
	}
}
