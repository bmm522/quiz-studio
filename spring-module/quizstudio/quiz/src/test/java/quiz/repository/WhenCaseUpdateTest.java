package quiz.repository;

import java.util.HashMap;
import javax.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

public class WhenCaseUpdateTest extends CUDNativeQueryActionTimeTest {


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
	void native_쿼리_case_when_실행시간_측정() {
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
