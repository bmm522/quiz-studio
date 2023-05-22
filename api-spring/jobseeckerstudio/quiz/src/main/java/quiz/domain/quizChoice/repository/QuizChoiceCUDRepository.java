package quiz.domain.quizChoice.repository;

import java.util.List;
import quiz.global.dto.CustomQuizDto;

public interface QuizChoiceCUDRepository {

	int updateAllContentAndIsAnswer(List<CustomQuizDto.Choice> quizChoices);

}
