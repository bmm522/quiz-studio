package quiz.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import quiz.domain.quiz.repository.QuizRepository;

@DataJpaTest
public class QuizQueryRepositoryTest {

	@Autowired
	QuizRepository quizRepository;

//	@Test
//	@DisplayName("자바, 데이터베이스 퀴즈데이터를 가져온다")
//	@Sql("classpath:db/testData.sql")
//	void 자바_데이터베이스_퀴즈데이터를_가져온다() {
//		List<QuizQueryDto> quizQueryDtoList = quizRepository.findQuizzesForRedisBy();
//
//		assertThat(quizQueryDtoList.size()).isEqualTo(7);
//	}

}
