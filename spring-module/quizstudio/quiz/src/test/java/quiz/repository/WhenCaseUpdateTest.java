package quiz.repository;

import javax.persistence.Query;
import org.junit.jupiter.api.Test;

public class WhenCaseUpdateTest extends CUDNativeQueryActionTimeTest {


	@Test
		//@Sql("classpath:db/native-query-test-data.sql")
	void native_쿼리_case_when_실행시간_측정() {

		StringBuilder sb = new StringBuilder("UPDATE quiz  SET quiz_title = CASE ");

		for (int i = iNum; i < iSize; i++) {
			sb.append(" WHEN quiz_id = " + i)
				.append(" THEN ")
				.append("'" + updateMap.get(Long.valueOf(i)) + "'");

			if (i == ifConditionNum) {
				sb.append(" ELSE quiz_title ");
			}
		}

		sb.append("END ");

		Query query = entityManager.createNativeQuery(sb.toString());

		long beforeTime = System.currentTimeMillis();
		int updateQueryCount = query.executeUpdate();
		long afterTime = System.currentTimeMillis();
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("when case update 소요 시간(ms) : " + secDiffTime);
	}
}
