package com.quizbatch.tasklets.makequiz.step3mapper;

import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoFromResponse;
import java.util.List;
import java.util.stream.Collectors;
import quiz.domain.category.Category;
import quiz.domain.quiz.Quiz;
import quiz.domain.quizChoice.QuizChoice;

public class QuizTransformer {

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
