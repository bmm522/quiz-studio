package quiz.domain.quiz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import quiz.domain.quiz.Quiz;

public interface QuizMySqlRepository extends JpaRepository<Quiz, Long>, QuizMySqlQueryRepository {



}
