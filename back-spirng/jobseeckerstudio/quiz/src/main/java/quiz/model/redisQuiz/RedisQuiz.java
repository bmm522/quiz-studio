package quiz.model.redisQuiz;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import javax.persistence.Id;
import java.util.List;

@RedisHash("quiz")
@Getter
public class RedisQuiz {

    @Id
    private String id;

    private String quizTitle;

    private List<RedisQuizChoices> quizChoices;

    @Builder
    public RedisQuiz(String id, String quizTitle, List<RedisQuizChoices> quizChoices) {
        this.id = id;
        this.quizTitle = quizTitle;
        this.quizChoices = quizChoices;
    }
    public static class RedisQuizChoices {
        private String choiceContent;
        private boolean isAnswer;

        @Builder
        public RedisQuizChoices(String choiceContent, boolean isAnswer) {
            this.choiceContent = choiceContent;
            this.isAnswer = isAnswer;
        }
    }


}
