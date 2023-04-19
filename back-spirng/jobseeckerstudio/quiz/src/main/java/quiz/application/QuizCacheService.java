package quiz.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import quiz.model.quiz.repository.dto.QuizDto;
import quiz.model.redisQuiz.RedisQuiz;
import quiz.model.redisQuiz.mapper.RedisMapper;
import quiz.model.redisQuiz.repository.QuizRedisRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuizCacheService {

    private final QuizRedisRepository quizRedisRepository;

    public void saveAll(List<QuizDto> quizDtoList) {
        List<RedisQuiz> redisQuizList = RedisMapper.toRedisQuiz(quizDtoList);
        quizRedisRepository.saveAll(redisQuizList);
    }

    public void deleteAll() {
        quizRedisRepository.deleteAll();
    }
}
