package com.jobseeckerstudio.quiz.entity.redisQuiz;

import org.springframework.data.redis.core.RedisHash;
import javax.persistence.Id;
import java.util.List;

@RedisHash("quiz")
public class RedisQuiz {

    @Id
    private String id;
    private String quizTitle;
    private String difficulty;
    private String categoryName;
    private List<RedisQuizChoices> quizChoices;
    public static class RedisQuizChoices {
        private String choiceContent;
        private boolean isAnswer;
    }


}
