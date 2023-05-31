package com.quizbatch.domain.quizschema;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import quiz.repository.quiz.dto.QuizQueryDto;

public class QuizSchemaMapper {

	public static List<QuizSchema> toQuizSchemas(List<QuizQueryDto> quizQueryDtoList) {
		AtomicInteger i = new AtomicInteger();
		return quizQueryDtoList.stream()
			.map(quizDto -> toQuizCache(quizDto, i.getAndIncrement()))
			.collect(Collectors.toList());
	}

	private static String generateQuizId(QuizQueryDto quizQueryDto, int index) {
		return quizQueryDto.getCategoryTitle() + "_" + index;
	}

	private static QuizSchema toQuizCache(QuizQueryDto quizQueryDto, int index) {
		List<QuizChoicesSchema> redisQuizChoicesList = toRedisQuizChoicesList(
			quizQueryDto.getChoices());
		return QuizSchema.builder()
			.id(generateQuizId(quizQueryDto, index))
			.quizTitle(quizQueryDto.getQuizTitle())
			.quizChoices(redisQuizChoicesList)
			.build();
	}

	private static List<QuizChoicesSchema> toRedisQuizChoicesList(
		List<QuizQueryDto.ChoiceDto> choiceDtoList) {
		return choiceDtoList.stream()
			.map(
				choiceDto -> new QuizChoicesSchema(choiceDto.getChoiceContent(),
					choiceDto.isAnswer()))
			.collect(Collectors.toList());
	}

}
