package quiz.model.redisQuiz.mapper;

import quiz.model.quiz.repository.dto.QuizDto;
import quiz.model.redisQuiz.RedisQuiz;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RedisMapper {

    public static List<RedisQuiz> toRedisQuiz(List<QuizDto> quizDtoList) {
        return quizDtoList.stream().map(quizDto -> {
            List<RedisQuiz.RedisQuizChoices> redisQuizChoicesList = quizDto.getChoiceDtos().stream()
                .map(choiceDto -> new RedisQuiz.RedisQuizChoices(choiceDto.getChoiceContent(), choiceDto.isAnswer()))
                .collect(Collectors.toList());

            return RedisQuiz.builder()
                .id(quizDto.getCategoryName() + "_" + quizDto.getDifficulty())
                .quizTitle(quizDto.getQuizTitle())
                .quizChoices(redisQuizChoicesList)
                .build();
        }).collect(Collectors.toList());
    }
}
