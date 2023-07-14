package quiz.repository;

import javax.persistence.Query;
import org.junit.jupiter.api.Test;


public class UnionUpdateTest extends CUDNativeQueryActionTimeTest {


	@Test
//	@Sql("classpath:db/native-query-test-data.sql")
	void native_쿼리_union_실행시간_측정() {

		StringBuilder sb = new StringBuilder("UPDATE quiz q JOIN (");

		for (int i = iNum; i < iSize; i++) {

			sb.append("SELECT ").append(i)
				.append(" AS new_quiz_id, '")
				.append(updateMap.get(Long.valueOf(i)))
				.append("' AS new_quiz_title");

			if (i != ifConditionNum) {
				sb.append(" UNION ALL ");

			}
		}

		sb.append(") n ON q.quiz_id = n.new_quiz_id ")
			.append("SET q.quiz_title = n.new_quiz_title");

		Query query = entityManager.createNativeQuery(sb.toString());

		long beforeTime = System.currentTimeMillis();
		int updateQueryCount = query.executeUpdate();
		long afterTime = System.currentTimeMillis();
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("union update 소요 시간(ms) : " + secDiffTime);
	}
}
