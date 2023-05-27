package com.quizbatch.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class QuizChoices {


	private String choiceContent;
	private boolean isAnswer;

	@Builder
	public QuizChoices(String choiceContent, boolean isAnswer) {
		this.choiceContent = choiceContent;
		this.isAnswer = isAnswer;
	}
}
