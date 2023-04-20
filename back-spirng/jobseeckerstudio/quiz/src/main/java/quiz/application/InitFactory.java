package quiz.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import quiz.model.quiz.repository.dto.QuizDto;
import quiz.model.redisQuiz.RedisQuiz;
import quiz.model.redisQuiz.mapper.RedisMapper;


import java.util.List;
@Component
public class InitFactory {



    @Autowired
    private QuizMysqlService quizMysqlService;

    @Autowired
    private QuizCache quizCache;


    public void initData() {
        List<QuizDto> quizDtoList = quizMysqlService.getAllForRedis();
        List<RedisQuiz> quizList = RedisMapper.toRedisQuiz(quizDtoList);
        quizCache.deleteAll();
        quizCache.saveAll(quizList);
    }
}
