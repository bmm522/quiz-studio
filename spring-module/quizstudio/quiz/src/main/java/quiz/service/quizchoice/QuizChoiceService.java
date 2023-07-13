package quiz.service.quizchoice;

import java.util.List;
import java.util.stream.Collectors;
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
	 * 퀴즈 선택지의 정보를 업데이트하는 메서드입니다. (@Transactional은 상위 클래스 QuizFacade에 있습니다.)
	 *
	 * @param request 퀴즈 업데이트 요청 정보
	 * @return 업데이트된 선택지의 개수
	 */
	public int updateWhenQuizUpdate(final QuizUpdateParam.Request request) {
		List<Choice> quizChoices = converterQuizChoice(request.getQuizzes());
		return quizChoiceRepository.updateAllContentAndIsAnswer(
			quizChoices);
	}

	public List<Choice> converterQuizChoice(List<CustomQuizDto> customQuizDtoList) {
		return customQuizDtoList.stream()
			.flatMap(quizDto -> quizDto.getChoices().stream())
			.limit(4L * customQuizDtoList.size())
			.collect(Collectors.toList());
	}
}
