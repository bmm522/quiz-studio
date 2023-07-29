package quiz.domain.quiz.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import quiz.global.dto.CustomQuizDto;

@Repository
@RequiredArgsConstructor
public class QuizCUDRepositoryImpl implements QuizCUDRepository {


	private final EntityManager entityManager;
//	public QuizCUDRepositoryImpl(EntityManager entityManager) {
//		this.queryFactory = new SQLQueryFactory();
//
//		quiz = QQuiz.quiz;
//	}
//	private final EntityManager entityManager;

	/**
	 * 커스텀 퀴즈 DTO 리스트를 사용하여 모든 퀴즈의 제목을 업데이트하는 메서드입니다.
	 *
	 * @param quizzes 커스텀 퀴즈 DTO 리스트
	 * @return 업데이트된 퀴즈의 개수
	 */

//	public int updateAllTitleByCustomQuizDto(
//		final List<CustomQuizDto> quizzes) {
//		queryFactory.query().unionAll(getSubQuery(quizzes));
//		return 0;
//	}
//
//
//	private List<SubQueryExpression<Tuple>> getSubQuery(List<CustomQuizDto> quizzes) {
//		List<SubQueryExpression<Tuple>> subQueryExpressionList = new ArrayList<>();
//		for (CustomQuizDto quizDto : quizzes) {
//
//			SubQueryExpression<Tuple> sql = queryFactory.query().select((Expression) quizDto);
//			subQueryExpressionList.add(sql);
//		}
//		return subQueryExpressionList;
//	}
	@Override
	public int updateAllTitleByCustomQuizDto(final List<CustomQuizDto> quizzes) {
		StringBuilder sb = new StringBuilder("UPDATE quiz q JOIN (");

		for (int i = 0; i < quizzes.size(); i++) {
			CustomQuizDto quiz = quizzes.get(i);
			sb.append("SELECT ").append(quiz.getQuizId())
				.append(" AS new_quiz_id, '")
				.append(quiz.getTitle())
				.append("' AS new_quiz_title");

			if (i != quizzes.size() - 1) {
				sb.append(" UNION ALL ");
			}
		}

		sb.append(") n ON q.quiz_id = n.new_quiz_id ")
			.append("SET q.quiz_title = n.new_quiz_title;");
		Query query = entityManager.createNativeQuery(sb.toString());
		return query.executeUpdate();
	}

}
