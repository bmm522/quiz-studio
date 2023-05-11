package quiz.domain.quizCache;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import javax.persistence.Id;
import java.util.List;

@RedisHash("quiz")
@Getter
public class QuizCache {

    @Id
    private String id;

    private String quizTitle;

    private List<QuizChoices> quizChoices;

    @Builder
    public QuizCache(String id, String quizTitle, List<QuizChoices> quizChoices) {
        this.id = id;
        this.quizTitle = quizTitle;
        this.quizChoices = quizChoices;
    }
    public static class QuizChoices {
        private String choiceContent;
        private boolean isAnswer;

        @Builder
        public QuizChoices(String choiceContent, boolean isAnswer) {
            this.choiceContent = choiceContent;
            this.isAnswer = isAnswer;
        }
    }


}
