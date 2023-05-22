package quiz.domain.quiz.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import quiz.domain.quiz.Quiz;
import quiz.repository.quiz.QuizQueryRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long>, QuizQueryRepository {


	void updateAll(List<Quiz> quizzes);
}
