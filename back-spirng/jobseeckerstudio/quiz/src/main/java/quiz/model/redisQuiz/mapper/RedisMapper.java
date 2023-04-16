package quiz.model.redisQuiz.mapper;

import quiz.model.quiz.repository.dto.QuizDto;
import quiz.model.redisQuiz.RedisQuiz;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RedisMapper {

    public static List<RedisQuiz> toRedisQuiz(List<QuizDto> quizDtoList) {
        AtomicInteger i = new AtomicInteger();
        return quizDtoList.stream().map(quizDto -> {
            List<RedisQuiz.RedisQuizChoices> redisQuizChoicesList = quizDto.getChoiceDtos().stream()
                .map(choiceDto -> new RedisQuiz.RedisQuizChoices(choiceDto.getChoiceContent(), choiceDto.isAnswer()))
                .collect(Collectors.toList());

            RedisQuiz redisQuiz =  RedisQuiz.builder()
                .id(quizDto.getCategoryName() + "_" + quizDto.getDifficulty() + i)
                .quizTitle(quizDto.getQuizTitle())
                .quizChoices(redisQuizChoicesList)
                .build();

            i.getAndIncrement();
            return redisQuiz;
        }).collect(Collectors.toList());
    }
}
