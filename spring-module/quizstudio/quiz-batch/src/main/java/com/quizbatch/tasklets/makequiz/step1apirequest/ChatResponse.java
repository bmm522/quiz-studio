package com.quizbatch.tasklets.makequiz.step1apirequest;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatResponse {

	private List<Choice> choices;

	// constructors, getters and setters

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	public static class Choice {

		private int index;
		private Message message;

		// constructors, getters and setters
	}
}
