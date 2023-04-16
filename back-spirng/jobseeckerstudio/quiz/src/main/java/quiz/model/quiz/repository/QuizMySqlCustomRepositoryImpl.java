package quiz.model.quiz.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import quiz.model.quiz.Quiz;
import quiz.model.quiz.repository.dto.QuizDto;
import quiz.model.quiz.repository.mapper.QuizMapper;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuizMySqlCustomRepositoryImpl implements QuizMySqlCustomRepository{

    private final EntityManager entityManager;
    public List<QuizDto> findQuizzes() {
        String jpql = "SELECT q FROM Quiz q";
        TypedQuery<Quiz> query = entityManager.createQuery(jpql, Quiz.class);
        return QuizMapper.toQuizDto(query.getResultList());
//        return query.getResultList();
    }
}
