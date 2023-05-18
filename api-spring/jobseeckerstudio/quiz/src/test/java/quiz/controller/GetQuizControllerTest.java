package quiz.controller;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quiz.domain.category.Category;
import quiz.domain.quiz.repository.dto.QuizQueryDto;
import quiz.domain.quiz.repository.dto.QuizQueryDto.ChoiceDto;
import quiz.domain.userCategory.UserCategory;

public class GetQuizControllerTest extends ControllerTest {

	Category category;

	UserCategory userCategory;

	protected final String testUserKey = "testUserKey";
	protected final String testCategoryName = "testCategoryName";

	protected final String testCategoryDescription = "testCategoryDescription";

	@BeforeEach
	void init() {
		setUp();
	}

	@Test
	@DisplayName("퀴즈 불러오기")
	void 퀴즈_불러오기() {
		List<ChoiceDto> choiceDtoList = List.of(ChoiceDto.builder()
				.choiceContent("예제 보기1")
				.isAnswer(true)
				.build(),
			ChoiceDto.builder()
				.choiceContent("예제 보기2")
				.isAnswer(false)
				.build(),
			ChoiceDto.builder()
				.choiceContent("예제 보기3")
				.isAnswer(false)
				.build(),
			ChoiceDto.builder()
				.choiceContent("예제 보기4")
				.isAnswer(false)
				.build()
		);
		List<QuizQueryDto> quizQueryDtos = List.of(
			QuizQueryDto.builder()
				.categoryName(testCategoryName)
				.quizTitle("예제문제 1")
				.choiceDtos(choiceDtoList)
				.build(),
			QuizQueryDto.builder()
				.categoryName(testCategoryName)
				.quizTitle("예제문제 2")
				.choiceDtos(choiceDtoList)
				.build(),
			QuizQueryDto.builder()
				.categoryName(testCategoryName)
				.quizTitle("예제문제 3")
				.choiceDtos(choiceDtoList)
				.build()

		);
//		when(quizService.get(anyString(), anyLong()))

	}

}
