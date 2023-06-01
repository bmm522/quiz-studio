package com.quizbatch.tasklets.makequiz.step2converter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuizDtoFromResponseQueue {

	private static final Queue<List<QuizDtoFromResponse>> quizDtoFromResponsesQueue = new LinkedList<>();

	private QuizDtoFromResponseQueue() {

	}


	/**
	 * 퀴즈 DTO 목록을 큐에 추가합니다.
	 *
	 * @param quizDtoFromResponses 퀴즈 DTO 목록
	 */
	public static void add(List<QuizDtoFromResponse> quizDtoFromResponses) {
		try {
			quizDtoFromResponsesQueue.add(quizDtoFromResponses);
		} catch (IllegalStateException e) {
			log.error("큐 공간 부족");
		}

	}

	/**
	 * 큐에서 하나의 퀴즈 DTO 목록을 가져옵니다.
	 *
	 * @return 퀴즈 DTO 목록
	 */
	public static List<QuizDtoFromResponse> getOne() {
		return quizDtoFromResponsesQueue.poll();
	}
}
