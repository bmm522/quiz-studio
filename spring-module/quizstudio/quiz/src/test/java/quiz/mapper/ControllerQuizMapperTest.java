package quiz.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quiz.controller.quiz.dto.QuizSaveBody;
import quiz.controller.quiz.mapper.ControllerQuizMapper;
import quiz.global.dto.CustomQuizDto;
import quiz.service.quiz.dto.QuizSaveParam;

public class ControllerQuizMapperTest {

	@Test
	@DisplayName("userKey와 CustomQuizDto리스트와 categoryId를 넣으면 QuizSaveParam을 반환한다")
	void userKey와_CustomQuizDto리스트와_categoryId를_넣으면_QuizSaveParam을_반환한다() {
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

		QuizSaveBody requestDto = QuizSaveBody.builder()
			.quizzes(quizDtoList)
			.build();

		QuizSaveParam.Request result = ControllerQuizMapper.toSaveParam("testUser",
			1000L,
			requestDto.getQuizzes());

		assertThat(result.getUserKey()).isEqualTo("testUser");
		assertThat(result.getQuizzes().size()).isEqualTo(3);
		assertThat(result.getQuizzesChoice(1).size()).isEqualTo(4);
		assertThat(result.getCategoryId()).isEqualTo(1000L);

	}

//	@Test
//	@DisplayName("정답이 한개가 아닐때")
//	void toSaveRequestTestWhenNotCorrectAnswer() {
//		List<CustomQuizDto> quizDtoList = new ArrayList<>();
//		quizDtoList.add(
//			CustomQuizDto.createForTest("예제 문제1", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 1)
//		);
//		quizDtoList.add(
//			CustomQuizDto.createForTest("예제 문제2", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 2)
//		);
//		quizDtoList.add(
//			CustomQuizDto.createForTest("예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 3)
//		);
//		quizDtoList.add(
//			CustomQuizDto.createForTest("정답 없는 문제", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 5)
//		);
//
//		Exception exception = assertThrows(NotCorrectAnswerException.class, () -> {
//			ControllerQuizMapper.checkCorrectAnswer(quizDtoList);
//		});
//
//		assertThat(exception.getMessage()).isEqualTo("정확히 하나의 답만 선택해야 합니다. 퀴즈의 현재 선택된 답안 수 : 0");
//
//
//	}

}
