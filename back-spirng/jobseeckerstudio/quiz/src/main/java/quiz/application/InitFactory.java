package quiz.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import quiz.model.quiz.repository.dto.QuizDto;


import java.util.List;
@Component
public class InitFactory {



    @Autowired
    private QuizMysqlService quizMysqlService;

    @Autowired
    private QuizCacheService quizCacheService;


    public void initData() {
        List<QuizDto> quizDtoList = quizMysqlService.getAllForRedis();
        quizCacheService.deleteAll();
        quizCacheService.saveAll(quizDtoList);
    }
}
