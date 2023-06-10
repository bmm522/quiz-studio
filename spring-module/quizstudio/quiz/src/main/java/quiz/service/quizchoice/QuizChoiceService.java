package quiz.service.quizchoice;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quiz.domain.quizChoice.repository.QuizChoiceRepository;
import quiz.global.dto.CustomQuizDto;
import quiz.global.dto.CustomQuizDto.Choice;
import quiz.service.quiz.dto.QuizUpdateParam;

@Service
@RequiredArgsConstructor
public class QuizChoiceService {

	private final QuizChoiceRepository quizChoiceRepository;

	/**
	 * 퀴즈 선택지의 정보를 업데이트하는 메서드입니다.
	 *
	 * @param request 퀴즈 업데이트 요청 정보
	 * @return 업데이트된 선택지의 개수
	 */
	public int updateWhenQuizUpdate(final QuizUpdateParam.Request request) {
		List<Choice> quizChoices = new ArrayList<>();
		for (int i = 0; i < request.getQuizzes().size(); i++) {
			List<CustomQuizDto.Choice> temp = request.getQuizChoiceFromIndex(i);
			for (int z = 0; z < 4; z++) {
				quizChoices.add(temp.get(z));
			}
		}
		return quizChoiceRepository.updateAllContentAndIsAnswer(
			quizChoices);
	}
}
