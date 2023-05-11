package quiz.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quiz.domain.quizCache.QuizCache;
import quiz.domain.quizCache.repository.QuizRedisRepository;

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
