package com.quizbatch.tasklets.makequiz.step3mapper;

import com.quizbatch.domain.category.Category;
import com.quizbatch.domain.quiz.Quiz;
import com.quizbatch.domain.quizchoice.QuizChoice;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoFromResponse;
import java.util.List;
import java.util.stream.Collectors;


public class QuizTransformer {

	/**
	 * 퀴즈 DTO 목록을 퀴즈로 변환합니다.
	 *
	 * @param quizDtoFromResponses 퀴즈 DTO 목록
	 * @param category             카테고리
	 * @return 퀴즈 목록
	 */
	public static List<Quiz> toQuizzes(final List<QuizDtoFromResponse> quizDtoFromResponses,
		final Category category) {
		return quizDtoFromResponses.stream()
			.map(quizDto -> {
				final Quiz quiz = createQuizFromRequest(quizDto);
				addChoicesToQuiz(quiz, quizDto.getChoices());
				quiz.addCategory(category);
				return quiz;
			})
			.collect(Collectors.toList());
	}

	private static Quiz createQuizFromRequest(final QuizDtoFromResponse quizDto) {
		return Quiz.builder()
			.quizTitle(quizDto.getTitle())
			.build();
	}

	private static void addChoicesToQuiz(final Quiz quiz,
		final List<QuizDtoFromResponse.Choice> choices) {
		choices.stream()
			.map(QuizTransformer::createQuizChoiceFromChoiceDto)
			.forEach(quiz::addChoice);
	}

	private static QuizChoice createQuizChoiceFromChoiceDto(
		final QuizDtoFromResponse.Choice choice) {
		return QuizChoice.builder()
			.choiceContent(choice.getContent())
			.isAnswer(choice.isAnswer())
			.build();
	}

}
