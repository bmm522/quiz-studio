package quiz.model.quiz.repository;

import quiz.model.quiz.Quiz;
import quiz.model.quiz.repository.dto.QuizDto;

import java.util.List;

public interface QuizMySqlCustomRepository {

    List<QuizDto> findQuizzes();
}
