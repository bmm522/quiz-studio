package quiz.controller.quiz.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class QuizGetCondition {

	private String userKey;

	private Long categoryId;

	private int page;

	private final int pageSize = 10;

	public int getOffset() {
		return (this.page - 1) * pageSize;
	}

}
