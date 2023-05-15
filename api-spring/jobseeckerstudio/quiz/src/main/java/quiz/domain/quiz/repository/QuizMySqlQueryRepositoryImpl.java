package quiz.domain.quiz.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import quiz.domain.quiz.QQuiz;
import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.repository.dto.QuizDto;
import quiz.domain.quiz.repository.mapper.QuizMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Random;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;


public class QuizMySqlQueryRepositoryImpl implements QuizMySqlQueryRepository {

    private final JPAQueryFactory queryFactory;

    public QuizMySqlQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<QuizDto> findQuizzes() {
        QQuiz quiz = QQuiz.quiz;
        List<Quiz> quizList = queryFactory
            .selectFrom(quiz)
            .where(quiz.category.categoryName.eq("java")
                .or(quiz.category.categoryName.eq("data_structure")))
            // .orderBy(Expressions.numberTemplate(Double.class, "function('RAND')").asc())
            .fetch();

        return QuizMapper.toQuizDto(quizList);
    }
}
