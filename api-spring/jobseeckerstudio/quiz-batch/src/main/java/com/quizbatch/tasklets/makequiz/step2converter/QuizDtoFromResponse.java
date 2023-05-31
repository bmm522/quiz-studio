package com.quizbatch.tasklets.makequiz.step2converter;

import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class QuizDtoFromResponse {

	private CategoryTitle categoryTitle;
	private String id;

	private String title;

	private List<Choice> choices;

	public void setId() {
		this.id = this.categoryTitle.get() + "_" + ((int) (Math.random() * 10000));
	}

	public void setCategoryTitle(CategoryTitle categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	public static class Choice {

		private String content;

		private boolean isAnswer;
	}
}
