package quiz.model.redisQuiz.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quiz.model.redisQuiz.RedisQuiz;
@Repository
public interface QuizRedisRepository extends CrudRepository<RedisQuiz, String> {
}
