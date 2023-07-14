package quiz.repository;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import quiz.domain.category.repository.CategoryRepository;
import quiz.domain.quiz.repository.QuizRepository;


@DataJpaTest
@ActiveProfiles("prod")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CUDNativeQueryActionTimeTest {


	final int iNum = 1000;

	final int iSize = 10001;

	final int ifConditionNum = 10000;

	@Autowired
	TestEntityManager testEntityManager;

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	CategoryRepository categoryRepository;

	EntityManager entityManager;
	Map<Long, String> updateMap;

	@BeforeEach
	void init() {
		entityManager = testEntityManager.getEntityManager();

		StringBuilder sb = new StringBuilder();

		sb.append("INSERT INTO quiz(quiz_id, category_id, quiz_title) VALUES ");

		for (int i = 1000; i < 101001; i++) {

			if (i == 101000) {
				sb.append("(" + i + ",1" + ",'dummyData" + i + "')");
				break;
			}

			sb.append("(" + i + ",1" + ",'dummyData" + i + "'),");


		}

		Query query = entityManager.createNativeQuery(sb.toString());

		query.executeUpdate();
		updateMap = new HashMap<>();
		for (int i = iNum; i < iSize; i++) {
			updateMap.put(Long.valueOf(i), "updateTitle" + i);
		}

	}

}
