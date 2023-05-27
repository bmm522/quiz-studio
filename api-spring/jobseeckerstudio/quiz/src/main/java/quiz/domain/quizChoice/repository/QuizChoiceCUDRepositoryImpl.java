package quiz.domain.quizChoice.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import quiz.global.dto.CustomQuizDto;
import quiz.global.dto.CustomQuizDto.Choice;

@RequiredArgsConstructor
public class QuizChoiceCUDRepositoryImpl implements QuizChoiceCUDRepository {

	private final EntityManager entityManager;

	@Override
	public int updateAllContentAndIsAnswer(List<Choice> quizChoices) {
		StringBuilder sb = new StringBuilder("UPDATE quiz_choice c JOIN (");

		for (int i = 0; i < quizChoices.size(); i++) {

			CustomQuizDto.Choice quizChoice = quizChoices.get(i);
			System.out.println(quizChoice.getChoiceId());
			sb.append("SELECT ")
				.append(quizChoice.getChoiceId())
				.append(" AS new_choice_id, '")
				.append(quizChoice.getContent())
				.append("' AS new_choice_content, ")
				.append(quizChoice.getIsAnswer())
				.append(" as new_is_answer");

			if (i != quizChoices.size() - 1) {
				sb.append(" UNION ALL ");
			}
		}

		sb.append(") n ON c.choice_id = n.new_choice_id ")
			.append("SET c.choice_content = n.new_choice_content, c.is_answer = n.new_is_answer");
		System.out.println(sb.toString());
		Query query = entityManager.createNativeQuery(sb.toString());
		return query.executeUpdate();

	}
}
