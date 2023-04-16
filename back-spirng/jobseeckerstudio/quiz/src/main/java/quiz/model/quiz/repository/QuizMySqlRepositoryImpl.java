package quiz.model.quiz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import quiz.model.quiz.Quiz;

import javax.persistence.TypedQuery;
import java.util.List;

public interface QuizMySqlRepositoryImpl extends JpaRepository<Quiz, Long>, QuizMySqlCustomRepository {



}
