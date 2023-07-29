package quiz.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import quiz.domain.quiz.QQuiz;
import quiz.global.dto.CustomQuizDto;

@SpringBootTest
public class QuizCUDRepositoryTest {


	SQLQueryFactory sqlQueryFactory;

	QQuiz quiz;

	@Autowired
	private DataSource dataSource;

	//
	@BeforeEach
	void init() {
		SQLTemplates templates = new H2Templates();
		Configuration configuration = new Configuration(templates);
		this.sqlQueryFactory = new SQLQueryFactory(configuration, dataSource);
		quiz = QQuiz.quiz;
	}

	@Sql("classpath:db/testData.sql")
	@Test
	void 업데이트_querydsl_unionAll_테스트() {

		List<CustomQuizDto> customQuizDtoList = List.of(
			CustomQuizDto.createForTest(1L, "업데이트 문제1", "업데이트 보기1", "업데이트 보기2", "업데이트 보기3",
				"업데이트보기4", 1),
			CustomQuizDto.createForTest(2L, "업데이트 문제2", "업데이트 보기1", "업데이트보기2", "업데이트 보기3",
				"업데이트보기4", 2),
			CustomQuizDto.createForTest(3L, "업데이트 문제3", "업데이트 보기1", "업데이트 보기2", "업데이트 보기3",
				"업데이트 보기4", 3)
		);

		SubQueryExpression<Tuple> sq1 = sqlQueryFactory.query()
			.select();
		SubQueryExpression<Tuple> sq2 = sqlQueryFactory.query()
			.select(quiz.id, quiz.quizTitle);
		sqlQueryFactory.query().unionAll(sq1, sq2);

		System.out.println(sqlQueryFactory.query().fetchCount());
	}


}
