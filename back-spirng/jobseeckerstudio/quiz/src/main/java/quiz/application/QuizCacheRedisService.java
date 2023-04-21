package quiz.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quiz.model.quizCache.QuizCache;
import quiz.model.quizCache.repository.QuizRedisRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizCacheRedisService implements quiz.application.QuizCache {

    private final QuizRedisRepository quizRedisRepository;

    public void saveAll(List<QuizCache> quizList){
        quizRedisRepository.saveAll(quizList);
    }

    public void deleteAll() {
        quizRedisRepository.deleteAll();
    }
}
