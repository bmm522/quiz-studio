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

    @PersistenceContext
    private EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public QuizMySqlQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory =new JPAQueryFactory(entityManager);
    }
    public List<QuizDto> findQuizzes() {
        QQuiz quiz = QQuiz.quiz;
        List<Quiz> quizList = queryFactory.selectFrom(quiz)
            .where(quiz.category.categoryId.eq(1).or(quiz.category.categoryId.eq(2)))
            .orderBy(Expressions.numberTemplate(Double.class, "function('RAND')").asc())
            .fetch();

        System.out.println("repo : " + quizList);
        // String jpql = "SELECT q FROM Quiz q where q.category.categoryId = 1 or q.category.categoryId = 2 ORDER BY RAND()";
        //
        // TypedQuery<Quiz> query = entityManager.createQuery(jpql, Quiz.class);
        return QuizMapper.toQuizDto(quizList);
//        return query.getResultList();
    }
}
