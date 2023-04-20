package quiz.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import quiz.model.quiz.repository.dto.QuizDto;
import quiz.model.redisQuiz.RedisQuiz;
import quiz.model.redisQuiz.mapper.RedisMapper;
import quiz.model.redisQuiz.repository.QuizRedisRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizCacheRedisService implements QuizCache{

    private final QuizRedisRepository quizRedisRepository;

    public void saveAll(List<RedisQuiz> quizList){
        quizRedisRepository.saveAll(quizList);
    }

    public void deleteAll() {
        quizRedisRepository.deleteAll();
    }
}
