package quiz.repository;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.repository.QuizMySqlQueryRepositoryImpl;
import quiz.domain.quiz.repository.QuizMySqlRepository;
import quiz.domain.quiz.repository.dto.QuizDto;

@DataJpaTest
public class QuizQueryRepositoryTest {

    @Autowired
    QuizMySqlRepository quizRepository;

    @Test
    @DisplayName("모든 퀴즈데이터를 가져온다")
    @Sql("classpath:db/testData.sql")
    void findQuizzes() {
        List<QuizDto> quizDtoList = quizRepository.findQuizzes();

        assertThat(quizDtoList.size()).isEqualTo(7);
    }

}
