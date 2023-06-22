package com.quizbatch.apirequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import com.quizbatch.tasklets.makequiz.step1apirequest.ChatRequest;
import org.junit.jupiter.api.Test;

public class ChatRequestTest {


	private final ChatRequest chatRequest = new ChatRequest();


	@Test
	void 자바_카테고리를_넣었을때_메세지_요청() {
		String message = chatRequest.addCategorySpecificMessage(CategoryTitle.JAVA);
		assertThat(message).isEqualTo(
			"자바와 관련된 cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘");
	}

	@Test
	void 자료구조_카테고리를_넣었을때_메세지_요청() {
		String message = chatRequest.addCategorySpecificMessage(CategoryTitle.DATA_STRUCTURE);
		assertThat(message).isEqualTo(
			"자료구조와 관련된 cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘");
	}

	@Test
	void 데이터베이스_카테고리를_넣었을때_메세지_요청() {
		String message = chatRequest.addCategorySpecificMessage(CategoryTitle.DATABASE);
		assertThat(message).isEqualTo(
			"데이터베이스와 관련된 cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘");
	}

	@Test
	void 스프링_카테고리를_넣었을때_메세지_요청() {
		String message = chatRequest.addCategorySpecificMessage(CategoryTitle.SPRING);
		assertThat(message).isEqualTo(
			"스프링 프레임워크와 관련된 cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘");
	}

	@Test
	void 네트워크_카테고리를_넣었을때_메세지_요청() {
		String message = chatRequest.addCategorySpecificMessage(CategoryTitle.NETWORK);
		assertThat(message).isEqualTo(
			"네트워크와 관련된 cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘");
	}

	@Test
	void 인터뷰_카테고리를_넣었을때_메세지_요청() {
		String message = chatRequest.addCategorySpecificMessage(CategoryTitle.INTERVIEW);
		assertThat(message).isEqualTo(
			"너가 면접관이 되서 면접자에게  cs 면접 문제를 5개 만들어줘, 단 정답은 무조건 한개이어야 하고, 선택지는 4개이어야 해, JSON 형식으로 만들어줘");
	}

}
