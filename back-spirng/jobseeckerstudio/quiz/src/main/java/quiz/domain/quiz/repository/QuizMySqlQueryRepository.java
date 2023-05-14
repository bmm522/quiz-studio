package quiz.domain.quiz.repository;

import quiz.domain.quiz.repository.dto.QuizDto;

import java.util.List;

public interface QuizMySqlQueryRepository {

    List<QuizDto> findQuizzes();
}
