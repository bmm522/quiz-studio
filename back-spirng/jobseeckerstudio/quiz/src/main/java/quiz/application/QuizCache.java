package quiz.application;

import java.util.List;

public interface QuizCache {

    void saveAll(List<quiz.domain.quizCache.QuizCache> quizList);
    void deleteAll();
}
