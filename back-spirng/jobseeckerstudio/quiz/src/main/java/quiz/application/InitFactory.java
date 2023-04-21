package quiz.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import quiz.model.quiz.repository.dto.QuizDto;
import quiz.model.quizCache.QuizCache;
import quiz.model.quizCache.mapper.QuizCacheTransformer;


import java.util.List;
@Component
public class InitFactory {



    @Autowired
    private QuizMysqlService quizMysqlService;

    @Autowired
    private quiz.application.QuizCache quizCache;


    public void initData() {
        List<QuizDto> quizDtoList = quizMysqlService.getAllForRedis();
        List<QuizCache> quizList = QuizCacheTransformer.toQuizCacheList(quizDtoList);
        quizCache.deleteAll();
        quizCache.saveAll(quizList);
    }
}
