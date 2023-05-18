package quiz.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import quiz.domain.category.Category;
import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.repository.QuizMySqlRepository;
import quiz.domain.quizChoice.QuizChoice;
import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.UserCategoryRepository;
import quiz.global.dto.CustomQuizDto;
import quiz.service.quiz.QuizService;
import quiz.service.quiz.dto.S_QuizSaveRequest;
import quiz.service.quiz.dto.S_QuizSaveResponse;

@ExtendWith(MockitoExtension.class)
public class SaveQuizServiceTest {

	QuizMySqlRepository quizRepository;

	UserCategoryRepository userCategoryRepository;
	QuizService quizService;

	@BeforeEach
	void init() {
		quizRepository = mock(QuizMySqlRepository.class);
		userCategoryRepository = mock(UserCategoryRepository.class);
		quizService = new QuizService(quizRepository, userCategoryRepository);
	}

	@Test
	@DisplayName("정상적인 요청")
	void saveTest() {
		List<CustomQuizDto> quizDtoList = createQuizDtoList();
		S_QuizSaveRequest request = S_QuizSaveRequest.builder()
			.quizzes(quizDtoList)
			.userKey("testUser")
			.categoryId(1000L)
			.build();

		Category category = Category.builder()
			.categoryName("testCategoryName")
			.categoryDescription("testCategoryDescription")
			.build();
		UserCategory userCategory = UserCategory.builder()
			.userKey("testUser")
			.category(category)
			.build();
		List<QuizChoice> quiz1Choices = createQuizChoices(true, false, false, false);
		List<QuizChoice> quiz2Choices = createQuizChoices(false, true, false, false);
		List<QuizChoice> quiz3Choices = createQuizChoices(false, false, true, false);

		Quiz quiz = Quiz.builder()
			.quizTitle("예제 문제1")
			.build();
		quiz.addChoices(quiz1Choices);

		Quiz quiz2 = Quiz.builder()
			.quizTitle("예제 문제2")
			.build();
		quiz2.addChoices(quiz2Choices);

		Quiz quiz3 = Quiz.builder()
			.quizTitle("예제 문제3")
			.build();
		quiz3.addChoices(quiz3Choices);

		List<Quiz> quizList = List.of(quiz, quiz2, quiz3);
		when(userCategoryRepository.findUserCategoryByUserCategoryId(any())).thenReturn(
			Optional.of(userCategory));
		when(quizRepository.saveAll(any())).thenReturn(quizList);

		S_QuizSaveResponse result = quizService.saveAll(request);

		assertThat(result.getUserKey()).isEqualTo("testUser");
		assertThat(result.getQuizzes().size()).isEqualTo(3);
	}

	private List<CustomQuizDto> createQuizDtoList() {
		return List.of(
			CustomQuizDto.createQuiz("예제 문제1", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 1),
			CustomQuizDto.createQuiz("예제 문제2", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 2),
			CustomQuizDto.createQuiz("예제 문제3", "예제 보기1", "예제 보기2", "예제 보기3", "예제 보기4", 3)
		);
	}

	private List<QuizChoice> createQuizChoices(boolean... isAnswers) {
		List<QuizChoice> choices = new ArrayList<>();
		for (int i = 0; i < isAnswers.length; i++) {
			choices.add(QuizChoice.builder()
				.choiceContent("예제 보기" + (i + 1))
				.isAnswer(isAnswers[i])
				.build());
		}
		return choices;
	}

}
