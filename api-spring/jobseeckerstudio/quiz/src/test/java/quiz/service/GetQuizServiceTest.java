package quiz.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quiz.repository.quiz.dto.QuizQueryDto;
import quiz.service.quiz.dto.QuizGetResponse;

public class GetQuizServiceTest extends ServiceTest {

	@BeforeEach
	void init() {
		setUp();
	}

	@Test
	@DisplayName("유저키와 카테고리 아이디로 해당 퀴즈 목록을 가져온다")
	void 유저키와_카테고리_아이디로_해당_퀴즈_목록을_가져온다() {
		List<QuizQueryDto> quizQeuryDtoList = List.of(
			QuizQueryDto.createForTest("예제 카테고리", "예제 문제1", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4",
				1),
			QuizQueryDto.createForTest("예제 카테고리", "예제 문제2", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4",
				2),
			QuizQueryDto.createForTest("예제 카테고리", "예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4",
				3),
			QuizQueryDto.createForTest("예제 카테고리", "예제 문제4", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4",
				4)
		);
		when(quizRepository.findQuizQueryDtoListByCategoryIdAndUserKey(anyString(),
			anyLong(), anyInt(), anyInt())).thenReturn(quizQeuryDtoList);

		QuizGetResponse result = quizService.getQuizzesWithPaging("testUser", 1000L, 1);

		assertThat(result.getQuizzes().size()).isEqualTo(4);
	}
}
