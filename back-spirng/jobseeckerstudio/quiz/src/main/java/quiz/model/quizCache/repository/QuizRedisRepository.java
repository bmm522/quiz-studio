package quiz.model.quizCache.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quiz.model.quizCache.QuizCache;
@Repository
public interface QuizRedisRepository extends CrudRepository<QuizCache, String> {
}
