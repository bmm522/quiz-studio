package com.quizbatch.tasklets.makequiz.step3mapper;

import com.quizbatch.domain.quiz.Quiz;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class QuizQueue {

	private static final Queue<List<Quiz>> quizzesQueue = new LinkedList<>();

	private QuizQueue() {

	}

	/**
	 * 퀴즈 목록을 큐에 추가합니다.
	 *
	 * @param quizzes 퀴즈 목록
	 */
	public static void add(List<Quiz> quizzes) {
		try {
			quizzesQueue.add(quizzes);
		} catch (IllegalStateException e) {
			log.error("큐 공간 부족");
		}

	}

	/**
	 * 큐에서 하나의 퀴즈 목록을 가져옵니다.
	 *
	 * @return 퀴즈 목록
	 */
	public static List<Quiz> getOne() {
		return quizzesQueue.poll();
	}

	/**
	 * 큐에 있는 모든 퀴즈 목록을 가져옵니다. 만약 큐가 비어있거나 퀴즈 목록이 비어있는 경우 빈 리스트를 반환합니다.
	 *
	 * @return 모든 퀴즈 목록
	 */
	public static List<Quiz> getAllFromQueue() {
		List<Quiz> quizzes = new ArrayList<>();
		int size = quizzesQueue.size();
		for (int i = 0; i < size; i++) {
			List<Quiz> quizzesFromQueue = quizzesQueue.poll();
			if (quizzesFromQueue == null || quizzesFromQueue.size() == 0) {
				return quizzes;
			}
			quizzes.addAll(quizzesFromQueue);
		}
		return quizzes;
	}

}
