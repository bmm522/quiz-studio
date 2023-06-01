package com.quizbatch.domain.quiz;

import java.util.List;
import java.util.stream.Collectors;

public class QuizMapper {

	public static List<QuizQueryDto> toQuizQueryDtoListForRedis(List<Quiz> quizList) {

		return quizList.stream().map(quiz -> {
			List<QuizQueryDto.ChoiceDto> choiceDtoList = quiz.getQuizChoices().stream()
				.map(choice -> new QuizQueryDto.ChoiceDto(
					choice.getChoiceContent(),
					choice.getIsAnswer()))
				.collect(Collectors.toList());

			return QuizQueryDto.builder()
				.categoryTitle(quiz.findCategoryName())
//				.difficulty(quiz.getDifficulty())
				.quizTitle(quiz.getQuizTitle())
				.choiceDtos(choiceDtoList)
				.build();

		}).collect(Collectors.toList());
	}

}
