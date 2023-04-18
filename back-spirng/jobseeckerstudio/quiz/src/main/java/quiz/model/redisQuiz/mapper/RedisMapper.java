package quiz.model.redisQuiz.mapper;

import quiz.model.quiz.repository.dto.QuizDto;
import quiz.model.redisQuiz.RedisQuiz;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RedisMapper {

    public static List<RedisQuiz> toRedisQuiz(List<QuizDto> quizDtoList) {
        AtomicInteger i = new AtomicInteger();
        return quizDtoList.stream()
            .map(quizDto -> toRedisQuiz(quizDto, i.getAndIncrement()))
            .collect(Collectors.toList());
    }

    private static String generateQuizId(QuizDto quizDto, int index) {
        return quizDto.getCategoryName() + "_" + quizDto.getDifficulty() + index;
    }

    private static RedisQuiz toRedisQuiz(QuizDto quizDto, int index) {
        List<RedisQuiz.RedisQuizChoices> redisQuizChoicesList = toRedisQuizChoicesList(quizDto.getChoiceDtos());

        return RedisQuiz.builder()
            .id(generateQuizId(quizDto, index))
            .quizTitle(quizDto.getQuizTitle())
            .quizChoices(redisQuizChoicesList)
            .build();
    }

    private static List<RedisQuiz.RedisQuizChoices> toRedisQuizChoicesList(List<QuizDto.ChoiceDto> choiceDtoList) {
        return choiceDtoList.stream()
            .map(choiceDto -> new RedisQuiz.RedisQuizChoices(choiceDto.getChoiceContent(), choiceDto.isAnswer()))
            .collect(Collectors.toList());
    }
}
