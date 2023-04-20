package quiz.application;

import quiz.model.quiz.repository.dto.QuizDto;
import quiz.model.redisQuiz.RedisQuiz;

import java.util.List;

public interface QuizCache {

    void saveAll(List<RedisQuiz> quizList);
    void deleteAll();
}
