package quiz.repository;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ActiveProfiles("prod")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CUDNativeQueryActionTimeTest {


	@Autowired
	TestEntityManager testEntityManager;

	EntityManager entityManager;
	Map<Long, String> updateMap;

	@BeforeEach
	void init() {
		entityManager = testEntityManager.getEntityManager();
		updateMap = new HashMap<>();
		for (int i = 1; i < 1001; i++) {
			updateMap.put(Long.valueOf(i), "updateTitle" + i);
		}

	}

	//	@Sql("classpath:db/testData.sql")
	@Test
	@Sql("classpath:db/native-query-test-data.sql")
	void 쌩쿼리_union_실행시간_측정() {

		long beforeTime = System.currentTimeMillis();

		StringBuilder sb = new StringBuilder("UPDATE quiz q JOIN (");

		for (int i = 1; i < 1001; i++) {
			sb.append("SELECT ").append(i)
				.append(" AS new_quiz_id, '")
				.append(updateMap.get(Long.valueOf(i)))
				.append("' AS new_quiz_title");

			if (i != 1000) {
				sb.append(" UNION ALL ");
			}
		}

		sb.append(") n ON q.quiz_id = n.new_quiz_id ")
			.append("SET q.quiz_title = n.new_quiz_title");

		Query query = entityManager.createNativeQuery(sb.toString());

		long afterTime = System.currentTimeMillis();
		long secDiffTime = (afterTime - beforeTime);
		int updateQueryCount = query.executeUpdate();
		System.out.println("시간차이(m) : " + secDiffTime);
	}

	@Test
	@Sql("classpath:db/native-query-test-data.sql")
	void 쌩쿼리_case_when_실행시간_측정() {
		long beforeTime = System.currentTimeMillis();

		StringBuilder sb = new StringBuilder("UPDATE quiz  SET quiz_title = CASE ");

		for (int i = 1; i < 1001; i++) {
			sb.append(" WHEN quiz_id = " + i)
				.append(" THEN ")
				.append("'" + updateMap.get(Long.valueOf(i)) + "'");

			if (i == 1000) {
				sb.append(" ELSE quiz_title ");
			}
		}

		sb.append("END ");

		Query query = entityManager.createNativeQuery(sb.toString());

		long afterTime = System.currentTimeMillis();
		long secDiffTime = (afterTime - beforeTime);
		int updateQueryCount = query.executeUpdate();
		System.out.println("시간차이(m) : " + secDiffTime);
	}
}
