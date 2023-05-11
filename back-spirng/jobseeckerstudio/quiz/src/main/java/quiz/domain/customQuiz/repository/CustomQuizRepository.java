package quiz.domain.customQuiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import quiz.domain.customQuiz.CustomQuiz;

public interface CustomQuizRepository extends JpaRepository<CustomQuiz, Long> {
}
