package quiz.domain.quizCache.mapper;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import quiz.domain.quizCache.QuizCache;
import quiz.repository.quiz.dto.QuizQueryDto;

public class QuizCacheTransformer {

	public static List<QuizCache> toQuizCacheList(List<QuizQueryDto> quizQueryDtoList) {
		AtomicInteger i = new AtomicInteger();
		return quizQueryDtoList.stream()
			.map(quizDto -> toQuizCache(quizDto, i.getAndIncrement()))
			.collect(Collectors.toList());
	}

	private static String generateQuizId(QuizQueryDto quizQueryDto, int index) {
		return quizQueryDto.getCategoryTitle() + "_" + quizQueryDto.getDifficulty() + index;
	}

	private static QuizCache toQuizCache(QuizQueryDto quizQueryDto, int index) {
		List<QuizCache.QuizChoices> redisQuizChoicesList = toRedisQuizChoicesList(
			quizQueryDto.getChoices());
		return QuizCache.builder()
			.id(generateQuizId(quizQueryDto, index))
			.quizTitle(quizQueryDto.getQuizTitle())
			.quizChoices(redisQuizChoicesList)
			.build();
	}

	private static List<QuizCache.QuizChoices> toRedisQuizChoicesList(
		List<QuizQueryDto.ChoiceDto> choiceDtoList) {
		return choiceDtoList.stream()
			.map(
				choiceDto -> new QuizCache.QuizChoices(choiceDto.getChoiceContent(),
					choiceDto.isAnswer()))
			.collect(Collectors.toList());
	}
}
