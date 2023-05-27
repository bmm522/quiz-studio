package com.quizbatch.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("quiz")
@Getter
@NoArgsConstructor
public class Quiz {

	@Id
	private String id;

	private String quizTitle;

	private List<QuizChoices> quizChoices;

	@Builder
	public Quiz(String id, String quizTitle, List<QuizChoices> quizChoices) {
		this.id = id;
		this.quizTitle = quizTitle;
		this.quizChoices = quizChoices;
	}

	@Builder
	public Quiz(String quizTitle, List<QuizChoices> quizChoices) {
		this.quizTitle = quizTitle;
		this.quizChoices = quizChoices;
	}

//	@NoArgsConstructor
//	@Getter
//
//	public static class QuizChoices {
//
//		private String choiceContent;
//		private boolean isAnswer;
//
//		@Builder
//		public QuizChoices(String choiceContent, boolean isAnswer) {
//			this.choiceContent = choiceContent;
//			this.isAnswer = isAnswer;
//		}
//	}


}
