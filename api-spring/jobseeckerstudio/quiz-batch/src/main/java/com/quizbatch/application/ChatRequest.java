package com.quizbatch.application;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatRequest {

	private String model;
	private List<Message> messages;

//	private int n;
//	private double temperature;

	public ChatRequest(String model) {
		this.model = model;
		StringBuilder firstAnswer = new StringBuilder();
		firstAnswer.append("[");
		firstAnswer.append(
			"{\"quizTitle\": \"1+1은?\", \"quizChoices\": [{\"choiceContent\": \"1\", \"isAnswer\": false},{\"choiceContent\": \"2\", \"isAnswer\": true},{\"choiceContent\": \"3\", \"isAnswer\": false},{\"choiceContent\": \"4\", \"isAnswer\": false} ]},");
		firstAnswer.append(
			"{\"quizTitle\": \"2+1은?\", \"quizChoices\": [{\"choiceContent\": \"1\", \"isAnswer\": false},{\"choiceContent\": \"2\", \"isAnswer\": false},{\"choiceContent\": \"3\", \"isAnswer\": true},{\"choiceContent\": \"4\", \"isAnswer\": false} ]},");
		firstAnswer.append(
			"{\"quizTitle\": \"3+1은?\", \"quizChoices\": [{\"choiceContent\": \"1\", \"isAnswer\": false},{\"choiceContent\": \"2\", \"isAnswer\": false},{\"choiceContent\": \"3\", \"isAnswer\": false},{\"choiceContent\": \"4\", \"isAnswer\": true} ]},");
		firstAnswer.append(
			"{\"quizTitle\": \"2+2은?\", \"quizChoices\": [{\"choiceContent\": \"1\", \"isAnswer\": false},{\"choiceContent\": \"2\", \"isAnswer\": false},{\"choiceContent\": \"3\", \"isAnswer\": false},{\"choiceContent\": \"4\", \"isAnswer\": true} ]}");
		firstAnswer.append("]");

		StringBuilder secondAnswer = new StringBuilder();

		secondAnswer.append("[");
		secondAnswer.append(
			"{\"quizTitle\": \"4-3은?\", \"quizChoices\": [{\"choiceContent\": \"1\", \"isAnswer\": true},{\"choiceContent\": \"2\", \"isAnswer\":false},{\"choiceContent\": \"5\", \"isAnswer\": false},{\"choiceContent\": \"4\", \"isAnswer\": false} ]},");
		secondAnswer.append(
			"{\"quizTitle\": \"7-2은?\", \"quizChoices\": [{\"choiceContent\": \"1\", \"isAnswer\": false},{\"choiceContent\": \"5\", \"isAnswer\":true},{\"choiceContent\": \"3\", \"isAnswer\": false},{\"choiceContent\": \"4\", \"isAnswer\": false} ]},");
		secondAnswer.append(
			"{\"quizTitle\": \"8-4은?\", \"quizChoices\": [{\"choiceContent\": \"3\", \"isAnswer\": false},{\"choiceContent\": \"2\", \"isAnswer\":false},{\"choiceContent\": \"5\", \"isAnswer\": false},{\"choiceContent\": \"4\", \"isAnswer\": true} ]}");
		secondAnswer.append("]");
		this.messages = new ArrayList<>();

		this.messages.add(new Message("system",
			"A question generator that creates multiple-choice questions where every question has only one correct answer and four choices."));
		this.messages.add(
			new Message("user",
				"덧셈과 관련된 cs 면접 문제를 4개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘"));
		this.messages.add(new Message
			("assistant", firstAnswer.toString()));
		this.messages.add(
			new Message("user",
				"뺄셈과 관련된 cs 면접 문제를 3개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘"));
		this.messages.add(new Message
			("assistant", secondAnswer.toString()));
		this.messages.add(new Message("user",
			"자바와 관련된 cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘"));
	}

}
