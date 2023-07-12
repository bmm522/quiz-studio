package quiz.repository;

import java.util.HashMap;
import javax.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;


public class DuplicateUpdateTest extends CUDNativeQueryActionTimeTest {

	@BeforeEach
	void init() {
		entityManager = testEntityManager.getEntityManager();
		updateMap = new HashMap<>();
		for (int i = 1; i < 1001; i++) {
			updateMap.put(Long.valueOf(i), "updateTitle" + i);
		}

	}

	@Test
	@Sql("classpath:db/native-query-test-data.sql")
	void native_쿼리_duplicate_update_on_실행시간_측정() {
		long beforeTime = System.currentTimeMillis();

		StringBuilder sb = new StringBuilder(
			"INSERT INTO quiz(quiz_id, category_id, quiz_title) values");

		for (int i = 1; i < 1001; i++) {
			sb.append("(" + i + ",2" + ",'" + updateMap.get(Long.valueOf(i)) + "'),");

			if (i == 1000) {
				sb.append("(" + i + ",2" + ",'" + updateMap.get(Long.valueOf(i))
					+ "') AS new_data ON DUPLICATE KEY UPDATE quiz_title = new_data.quiz_title");
			}
		}

		Query query = entityManager.createNativeQuery(sb.toString());

		long afterTime = System.currentTimeMillis();
		long secDiffTime = (afterTime - beforeTime);
		int updateQueryCount = query.executeUpdate();
		System.out.println("시간차이(m) : " + secDiffTime);
	}
}
