package com.quizbatch.tasklets.makequiz.step1apirequest;

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

	private float temperature;

//	private int n;
//	private double temperature;

	public ChatRequest(String model, CategoryTitle categoryTitle) {
		this.model = model;
		String firstAnswer = "["
			+ "{\"title\": \"1+1은?\", \"choices\": [{\"content\": \"1\", \"isAnswer\": false},{\"content\": \"2\", \"isAnswer\": true},{\"content\": \"3\", \"isAnswer\": false},{\"content\": \"4\", \"isAnswer\": false} ]},"
			+ "{\"title\": \"2+1은?\", \"choices\": [{\"content\": \"1\", \"isAnswer\": false},{\"content\": \"2\", \"isAnswer\": false},{\"content\": \"3\", \"isAnswer\": true},{\"content\": \"4\", \"isAnswer\": false} ]},"
			+ "{\"title\": \"3+1은?\", \"choices\": [{\"content\": \"1\", \"isAnswer\": false},{\"content\": \"2\", \"isAnswer\": false},{\"content\": \"3\", \"isAnswer\": false},{\"content\": \"4\", \"isAnswer\": true} ]},"
			+ "{\"title\": \"2+2은?\", \"choices\": [{\"content\": \"1\", \"isAnswer\": false},{\"content\": \"2\", \"isAnswer\": false},{\"content\": \"3\", \"isAnswer\": false},{\"content\": \"4\", \"isAnswer\": true} ]}"
			+ "]";

		String secondAnswer = "["
			+ "{\"title\": \"4-3은?\", \"choices\": [{\"content\": \"1\", \"isAnswer\": true},{\"content\": \"2\", \"isAnswer\":false},{\"content\": \"5\", \"isAnswer\": false},{\"content\": \"4\", \"isAnswer\": false} ]},"
			+ "{\"title\": \"7-2은?\", \"choices\": [{\"content\": \"1\", \"isAnswer\": false},{\"content\": \"5\", \"isAnswer\":true},{\"content\": \"3\", \"isAnswer\": false},{\"content\": \"4\", \"isAnswer\": false} ]},"
			+ "{\"title\": \"8-4은?\", \"choices\": [{\"content\": \"3\", \"isAnswer\": false},{\"content\": \"2\", \"isAnswer\":false},{\"content\": \"5\", \"isAnswer\": false},{\"content\": \"4\", \"isAnswer\": true} ]}"
			+ "]";
		this.messages = new ArrayList<>();

		this.messages.add(new Message("system",
			"A question generator that creates multiple-choice questions where every question has only one correct answer and four choices."));
		this.messages.add(
			new Message("user",
				"덧셈과 관련된 cs 면접 문제를 4개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘"));
		this.messages.add(new Message
			("assistant", firstAnswer));
		this.messages.add(
			new Message("user",
				"뺄셈과 관련된 cs 면접 문제를 3개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘"));
		this.messages.add(new Message
			("assistant", secondAnswer));

		this.messages.add(new Message("user", addCategorySpecificMessage(categoryTitle)));

		this.temperature = 0.5f;
	}


	public String addCategorySpecificMessage(CategoryTitle categoryTitle) {
		switch (categoryTitle) {
			case JAVA:
				return "자바와 관련된 cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘";
			case DATA_STRUCTURE:
				return "자료구조와 관련된 cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘";
			case DATABASE:
				return "데이터베이스와 관련된 cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘";
			case SPRING:
				return "스프링 프레임워크와 관련된 cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘";
			case NETWORK:
				return "네트워크와 관련된 cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘";
			case INTERVIEW:
				return "너가 면접관이 되서 면접자에게  cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘";
			default:
				return "없는 카테고리";
		}
	}
}
