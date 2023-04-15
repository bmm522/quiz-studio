package quiz.model.quizChoice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import quiz.model.quizChoice.QuizChoice;

public interface QuizChoiceRepository extends JpaRepository<QuizChoice, Long> {
}
