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
		System.out.println(sb.toString());
		Query query = entityManager.createNativeQuery(sb.toString());
		return query.executeUpdate();
	}

}
