package quiz.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quiz.controller.quiz.dto.C_QuizSaveRequest;
import quiz.controller.quiz.mapper.C_QuizMapper;
import quiz.global.dto.CustomQuizDto;
import quiz.global.exception.NotCorrectAnswerException;
import quiz.service.quiz.dto.S_QuizSaveRequest;

public class C_QuizMapperTest {

	@Test
	void toSaveRequestTest() {
		List<CustomQuizDto> quizDtoList = new ArrayList<>();
		quizDtoList.add(
			CustomQuizDto.createForTest("예제 문제1", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 1)
		);
		quizDtoList.add(
			CustomQuizDto.createForTest("예제 문제2", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 2)
		);
		quizDtoList.add(
			CustomQuizDto.createForTest("예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 3)
		);

		C_QuizSaveRequest requestDto = C_QuizSaveRequest.builder()
			.quizzes(quizDtoList)
			.build();

		S_QuizSaveRequest result = C_QuizMapper.toSaveRequest("testUser", requestDto, 1000L);

		assertThat(result.getUserKey()).isEqualTo("testUser");
		assertThat(result.getQuizzes().size()).isEqualTo(3);
		assertThat(result.getQuizzesChoice(1).size()).isEqualTo(4);
		assertThat(result.getCategoryId()).isEqualTo(1000L);

	}

	@Test
	@DisplayName("정답이 한개가 아닐때")
	void toSaveRequestTestWhenNotCorrectAnswer() {
		List<CustomQuizDto> quizDtoList = new ArrayList<>();
		quizDtoList.add(
			CustomQuizDto.createForTest("예제 문제1", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 1)
		);
		quizDtoList.add(
			CustomQuizDto.createForTest("예제 문제2", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 2)
		);
		quizDtoList.add(
			CustomQuizDto.createForTest("예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 3)
		);
		quizDtoList.add(
			CustomQuizDto.createForTest("정답 없는 문제", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 5)
		);

		Exception exception = assertThrows(NotCorrectAnswerException.class, () -> {
			C_QuizMapper.checkCorrectAnswer(quizDtoList);
		});

		assertThat(exception.getMessage()).isEqualTo("정확히 하나의 답만 선택해야 합니다. 퀴즈의 현재 선택된 답안 수 : 0");


	}

}
