package quiz.model.quiz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import quiz.model.quiz.Quiz;

public interface QuizMySqlRepository extends JpaRepository<Quiz, Long>, QuizMySqlCustomRepository {



}
