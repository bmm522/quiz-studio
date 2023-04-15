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
    private String difficulty;
    private String categoryName;

    private String quizTitle;

    private List<RedisQuizChoices> quizChoices;

    @Builder
    public RedisQuiz(String difficulty, String categoryName, String quizTitle, List<RedisQuizChoices> quizChoicesList) {
        this.difficulty = difficulty;
        this.categoryName = categoryName;
        this.quizTitle = quizTitle;
        this.quizChoices = quizChoicesList;
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
