package com.quizbatch.tasklets.makequiz.step2converter;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class QuizDtoFromResponse {

	private String id;

	private String title;

	private List<Choice> choices;

	public void setId(String categoryTitle) {
		this.id = categoryTitle + "_" + ((int) (Math.random() * 10000));
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	public static class Choice {

		private String content;

		private boolean isAnswer;
	}
}
