package quiz.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.mapper.QuizMapper;
import quiz.global.dto.CustomQuizDto;

public class QuizMapperTest {

	@Test
	@DisplayName("커스텀 퀴즈 DTO를 넣으면 퀴즈 ENTITY를 반환한다.")
	void 커스텀_퀴즈_DTO를_넣으면_퀴즈_ENTITY를_반환환다() {
		List<CustomQuizDto> quizDtoList = new ArrayList<>();
		quizDtoList.add(
			CustomQuizDto.createQuiz("예제 문제1", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 1)
		);
		quizDtoList.add(
			CustomQuizDto.createQuiz("예제 문제2", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 2)
		);
		quizDtoList.add(
			CustomQuizDto.createQuiz("예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 3)
		);

		List<Quiz> result = QuizMapper.toEntitiesWhenSave(quizDtoList);
		assertThat(result.get(0).getQuizTitle()).isEqualTo("예제 문제1");
		assertThat(result.get(1).getQuizChoices().get(0).getChoiceContent()).isEqualTo("예제 보기1");
		assertThat(result.get(1).getQuizChoices().size()).isEqualTo(4);
	}

}
