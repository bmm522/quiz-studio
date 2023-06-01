package com.quizbatch.domain.quizschema;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("quiz")
@Getter
@NoArgsConstructor
public class QuizSchema {

	@Id
	private String id;

	private String quizTitle;

	private List<QuizChoicesSchema> quizChoices;

	@Builder
	public QuizSchema(String id, String quizTitle, List<QuizChoicesSchema> quizChoices) {
		this.id = id;
		this.quizTitle = quizTitle;
		this.quizChoices = quizChoices;
	}

	@Builder
	public QuizSchema(String quizTitle, List<QuizChoicesSchema> quizChoices) {
		this.quizTitle = quizTitle;
		this.quizChoices = quizChoices;
	}

	public void setId(String categoryTitle) {
		this.id = categoryTitle + "_" + ((int) (Math.random() * 10000));
	}

//	@NoArgsConstructor
//	@Getter
//
//	public static class QuizChoicesSchema {
//
//		private String choiceContent;
//		private boolean isAnswer;
//
//		@Builder
//		public QuizChoicesSchema(String choiceContent, boolean isAnswer) {
//			this.choiceContent = choiceContent;
//			this.isAnswer = isAnswer;
//		}
//	}


}
