package quiz.repository;

import javax.persistence.Query;
import org.junit.jupiter.api.Test;


public class DuplicateUpdateTest extends CUDNativeQueryActionTimeTest {


	@Test
		//@Sql("classpath:db/native-query-test-data.sql")
	void native_쿼리_duplicate_update_on_실행시간_측정() {

		StringBuilder sb = new StringBuilder(
			"INSERT INTO quiz(quiz_id, category_id, quiz_title) values");

		for (int i = iNum; i < iSize; i++) {
			if (i == ifConditionNum) {
				sb.append("(" + i + ",1" + ",'" + updateMap.get(Long.valueOf(i))
					+ "') AS new_data ON DUPLICATE KEY UPDATE quiz_title = new_data.quiz_title");

				break;
			}
			sb.append("(" + i + ",1" + ",'" + updateMap.get(Long.valueOf(i)) + "'),");


		}

		Query query = entityManager.createNativeQuery(sb.toString());
		long beforeTime = System.currentTimeMillis();

		int updateQueryCount = query.executeUpdate();
		long afterTime = System.currentTimeMillis();
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("insert duplicate update 소요 시간(ms) : " + secDiffTime);
	}
}
