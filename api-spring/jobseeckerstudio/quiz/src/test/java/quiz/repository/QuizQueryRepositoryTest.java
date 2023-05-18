package quiz.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import quiz.domain.quiz.repository.QuizMySqlRepository;
import quiz.repository.quiz.dto.QuizQueryDto;

@DataJpaTest
public class QuizQueryRepositoryTest {

	@Autowired
	QuizMySqlRepository quizRepository;

	@Test
	@DisplayName("모든 퀴즈데이터를 가져온다")
	@Sql("classpath:db/testData.sql")
	void 모든_퀴즈데이터를_가져온다() {
		List<QuizQueryDto> quizQueryDtoList = quizRepository.findQuizzes();

		assertThat(quizQueryDtoList.size()).isEqualTo(7);
	}

}
