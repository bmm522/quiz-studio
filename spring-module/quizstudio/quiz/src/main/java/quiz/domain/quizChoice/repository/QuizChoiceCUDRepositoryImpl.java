package quiz.domain.quizChoice.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import quiz.global.dto.CustomQuizDto.Choice;

@RequiredArgsConstructor
public class QuizChoiceCUDRepositoryImpl implements QuizChoiceCUDRepository {

	private final EntityManager entityManager;

	/**
	 * 커스텀 퀴즈 선택지 DTO 리스트를 사용하여 모든 선택지의 내용과 정답 여부를 업데이트하는 메서드입니다.
	 *
	 * @param quizChoices 커스텀 퀴즈 선택지 DTO 리스트
	 * @return 업데이트된 선택지의 개수
	 */
	@Override
	public int updateAllContentAndIsAnswer(final List<Choice> quizChoices) {
		StringBuilder sb = new StringBuilder("UPDATE quiz_choice c JOIN (");
		int i = 0;

		for (Choice quizChoice : quizChoices) {
			sb.append("SELECT ")
				.append(quizChoice.getChoiceId())
				.append(" AS new_choice_id, '")
				.append(quizChoice.getContent())
				.append("' AS new_choice_content, ")
				.append(quizChoice.getIsAnswer())
				.append(" AS new_is_answer");

			if (i != quizChoices.size() - 1) {
				sb.append(" UNION ALL ");
			}
			i++;
		}
		sb.append(") n ON c.choice_id = n.new_choice_id ")
			.append("SET c.choice_content = n.new_choice_content, c.is_answer = n.new_is_answer");
		Query query = entityManager.createNativeQuery(sb.toString());
		return query.executeUpdate();
	}
}
