package com.quizbatch.domain.quizschema;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class QuizChoicesSchema {


	private String choiceContent;
	private boolean isAnswer;

	@Builder
	public QuizChoicesSchema(String choiceContent, boolean isAnswer) {
		this.choiceContent = choiceContent;
		this.isAnswer = isAnswer;
	}
}
