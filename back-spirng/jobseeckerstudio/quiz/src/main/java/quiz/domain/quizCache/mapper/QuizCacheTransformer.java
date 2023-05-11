package quiz.domain.quizCache.mapper;

import quiz.domain.quiz.repository.dto.QuizDto;
import quiz.domain.quizCache.QuizCache;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class QuizCacheTransformer {

    public static List<QuizCache> toQuizCacheList(List<QuizDto> quizDtoList) {
        AtomicInteger i = new AtomicInteger();
        return quizDtoList.stream()
            .map(quizDto -> toQuizCache(quizDto, i.getAndIncrement()))
            .collect(Collectors.toList());
    }

    private static String generateQuizId(QuizDto quizDto, int index) {
        return quizDto.getCategoryName() + "_" + quizDto.getDifficulty() + index;
    }

    private static QuizCache toQuizCache(QuizDto quizDto, int index) {
        List<QuizCache.QuizChoices> redisQuizChoicesList = toRedisQuizChoicesList(quizDto.getChoiceDtos());

        return QuizCache.builder()
            .id(generateQuizId(quizDto, index))
            .quizTitle(quizDto.getQuizTitle())
            .quizChoices(redisQuizChoicesList)
            .build();
    }

    private static List<QuizCache.QuizChoices> toRedisQuizChoicesList(List<QuizDto.ChoiceDto> choiceDtoList) {
        return choiceDtoList.stream()
            .map(choiceDto -> new QuizCache.QuizChoices(choiceDto.getChoiceContent(), choiceDto.isAnswer()))
            .collect(Collectors.toList());
    }
}
