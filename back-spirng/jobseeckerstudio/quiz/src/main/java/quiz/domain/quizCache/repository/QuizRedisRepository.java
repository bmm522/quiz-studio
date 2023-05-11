package quiz.domain.quizCache.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quiz.domain.quizCache.QuizCache;
@Repository
public interface QuizRedisRepository extends CrudRepository<QuizCache, String> {
}
