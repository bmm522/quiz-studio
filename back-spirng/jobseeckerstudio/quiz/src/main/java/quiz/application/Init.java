package quiz.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import quiz.model.quiz.Quiz;
import quiz.model.quiz.repository.QuizMySqlRepositoryImpl;
import quiz.model.quiz.repository.dto.QuizDto;
import quiz.model.quizChoice.QuizChoice;
import quiz.model.redisQuiz.RedisQuiz;
import quiz.model.redisQuiz.mapper.RedisMapper;
import quiz.model.redisQuiz.repository.QuizRedisRepository;

import java.util.ArrayList;
import java.util.List;
@Component
public class Init {

    @Autowired
    private QuizRedisRepository quizRedisRepository;

    @Autowired
    private QuizMySqlRepositoryImpl quizMySqlRepository;

    @Autowired
    private RedisTemplate<byte[], byte[]> redisTemplate;


    public void initData() {

        redisTemplate.getConnectionFactory().getConnection().flushAll();
        List<QuizDto> quizDtoList = quizMySqlRepository.findQuizzes();
        List<RedisQuiz> redisQuizList = RedisMapper.toRedisQuiz(quizDtoList);

        quizRedisRepository.saveAll(redisQuizList);

    }
}
