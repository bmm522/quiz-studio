package com.quizbatch.domain.quizschema;

import com.quizbatch.domain.quiz.QuizQueryDto;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class QuizSchemaMapper {

	/**
	 * 퀴즈 쿼리 DTO 목록을 퀴즈 스키마 목록으로 변환하는 메서드입니다.
	 *
	 * @param quizQueryDtoList 퀴즈 쿼리 DTO 목록
	 * @return 퀴즈 스키마 목록
	 */
	public static List<QuizSchema> toQuizSchemas(final List<QuizQueryDto> quizQueryDtoList) {
		AtomicInteger i = new AtomicInteger();
		return quizQueryDtoList.stream()
			.map(quizDto -> toQuizCache(quizDto, i.getAndIncrement()))
			.collect(Collectors.toList());
	}

	/**
	 * 퀴즈 ID를 생성하는 메서드입니다.
	 *
	 * @param quizQueryDto 퀴즈 쿼리 DTO
	 * @param index        인덱스
	 * @return 퀴즈 ID
	 */
	private static String generateQuizId(final QuizQueryDto quizQueryDto, final int index) {
		return quizQueryDto.getCategoryTitle() + "_" + index;
	}

	private static QuizSchema toQuizCache(final QuizQueryDto quizQueryDto, final int index) {
		final List<QuizChoicesSchema> redisQuizChoicesList = toRedisQuizChoicesList(
			quizQueryDto.getChoices());
		return QuizSchema.builder()
			.id(generateQuizId(quizQueryDto, index))
			.quizTitle(quizQueryDto.getQuizTitle())
			.quizChoices(redisQuizChoicesList)
			.build();
	}

	private static List<QuizChoicesSchema> toRedisQuizChoicesList(
		final List<QuizQueryDto.ChoiceDto> choiceDtoList) {
		return choiceDtoList.stream()
			.map(
				choiceDto -> new QuizChoicesSchema(choiceDto.getChoiceContent(),
					choiceDto.isAnswer()))
			.collect(Collectors.toList());
	}

}
