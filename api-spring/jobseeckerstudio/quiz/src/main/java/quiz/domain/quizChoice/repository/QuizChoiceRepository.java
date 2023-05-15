package quiz.domain.quizChoice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import quiz.domain.quizChoice.QuizChoice;

public interface QuizChoiceRepository extends JpaRepository<QuizChoice, Long> {
}
