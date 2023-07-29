package quiz.service;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import quiz.domain.quizChoice.repository.QuizChoiceRepository;
import quiz.global.dto.CustomQuizDto;
import quiz.global.dto.CustomQuizDto.Choice;
import quiz.service.quizchoice.QuizChoiceService;

@ExtendWith(MockitoExtension.class)
public class QuizChoiceServiceTest {


	@Mock
	QuizChoiceRepository quizChoiceRepository;

	@InjectMocks
	QuizChoiceService quizChoiceService;

	@Test
	void 퀴즈선택지_세팅_테스트() {
		List<CustomQuizDto> customQuizDtoList = List.of(
			CustomQuizDto.createForTest("예제 문제1", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 1),
			CustomQuizDto.createForTest("예제 문제2", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 2),
			CustomQuizDto.createForTest("예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 3)
		);

		List<Choice> choices = quizChoiceService.settingQuizChoice(customQuizDtoList);

		assertThat(choices.size()).isEqualTo(12);

	}


}
