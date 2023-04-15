package quiz.model.quiz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quiz.model.quiz.Quiz;

import java.util.List;

public interface QuizMySqlRepository extends JpaRepository<Quiz, Long> {
    @Query(value = "SELECT * FROM quiz_mysql ", nativeQuery = true)
    List<Quiz> findQuizzes();

    List<Quiz> findAll();
}
