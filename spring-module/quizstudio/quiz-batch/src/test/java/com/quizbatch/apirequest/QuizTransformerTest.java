package com.quizbatch.apirequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.quizbatch.domain.category.Category;
import com.quizbatch.domain.quiz.Quiz;
import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoConverter;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoFromResponse;
import com.quizbatch.tasklets.makequiz.step3mapper.QuizTransformer;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuizTransformerTest {


	String responseJsonFromOPENAIAPI;

	List<QuizDtoFromResponse> quizDtoFromResponses;

	@BeforeEach
	void setUp() {
		responseJsonFromOPENAIAPI = "["
			+ "{\"title\": \"4-3은?\", \"choices\": [{\"content\": \"1\", \"isAnswer\": true},{\"content\": \"2\", \"isAnswer\":false},{\"content\": \"5\", \"isAnswer\": false},{\"content\": \"4\", \"isAnswer\": false} ]},"
			+ "{\"title\": \"7-2은?\", \"choices\": [{\"content\": \"1\", \"isAnswer\": false},{\"content\": \"5\", \"isAnswer\":true},{\"content\": \"3\", \"isAnswer\": false},{\"content\": \"4\", \"isAnswer\": false} ]},"
			+ "{\"title\": \"8-4은?\", \"choices\": [{\"content\": \"3\", \"isAnswer\": false},{\"content\": \"2\", \"isAnswer\":false},{\"content\": \"5\", \"isAnswer\": false},{\"content\": \"4\", \"isAnswer\": true} ]}"
			+ "]";
	}

	@Test
	void 자바_카테고리가_담긴_퀴즈_Response_dto_로부터_Quiz_엔티티로_변환한다() {
		Category category = Category.builder()
			.categoryTitle(CategoryTitle.JAVA.get())
			.build();

		quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(responseJsonFromOPENAIAPI,
			CategoryTitle.JAVA);

		List<Quiz> quizzes = QuizTransformer.toQuizzes(quizDtoFromResponses, category);

		assertThat(quizzes.get(0).getQuizChoices().size()).isEqualTo(4);
		assertThat(quizzes.get(0).getCategory().getCategoryTitle()).isEqualTo(
			CategoryTitle.JAVA.get());
	}

	@Test
	void 자료구조_카테고리가_담긴_퀴즈_Response_dto_로부터_Quiz_엔티티로_변환한다() {
		Category category = Category.builder()
			.categoryTitle(CategoryTitle.DATA_STRUCTURE.get())
			.build();

		quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(responseJsonFromOPENAIAPI,
			CategoryTitle.DATA_STRUCTURE);

		List<Quiz> quizzes = QuizTransformer.toQuizzes(quizDtoFromResponses, category);

		assertThat(quizzes.get(0).getQuizChoices().size()).isEqualTo(4);
		assertThat(quizzes.get(0).getCategory().getCategoryTitle()).isEqualTo(
			CategoryTitle.DATA_STRUCTURE.get());
	}

	@Test
	void 데이터베이스_카테고리가_담긴_퀴즈_Response_dto_로부터_Quiz_엔티티로_변환한다() {
		Category category = Category.builder()
			.categoryTitle(CategoryTitle.DATABASE.get())
			.build();

		quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(responseJsonFromOPENAIAPI,
			CategoryTitle.DATABASE);

		List<Quiz> quizzes = QuizTransformer.toQuizzes(quizDtoFromResponses, category);

		assertThat(quizzes.get(0).getQuizChoices().size()).isEqualTo(4);
		assertThat(quizzes.get(0).getCategory().getCategoryTitle()).isEqualTo(
			CategoryTitle.DATABASE.get());
	}

	@Test
	void 스프링_카테고리가_담긴_퀴즈_Response_dto_로부터_Quiz_엔티티로_변환한다() {
		Category category = Category.builder()
			.categoryTitle(CategoryTitle.SPRING.get())
			.build();

		quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(responseJsonFromOPENAIAPI,
			CategoryTitle.SPRING);

		List<Quiz> quizzes = QuizTransformer.toQuizzes(quizDtoFromResponses, category);

		assertThat(quizzes.get(0).getQuizChoices().size()).isEqualTo(4);
		assertThat(quizzes.get(0).getCategory().getCategoryTitle()).isEqualTo(
			CategoryTitle.SPRING.get());
	}

	@Test
	void 네트워크_카테고리가_담긴_퀴즈_Response_dto_로부터_Quiz_엔티티로_변환한다() {
		Category category = Category.builder()
			.categoryTitle(CategoryTitle.NETWORK.get())
			.build();

		quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(responseJsonFromOPENAIAPI,
			CategoryTitle.NETWORK);

		List<Quiz> quizzes = QuizTransformer.toQuizzes(quizDtoFromResponses, category);

		assertThat(quizzes.get(0).getQuizChoices().size()).isEqualTo(4);
		assertThat(quizzes.get(0).getCategory().getCategoryTitle()).isEqualTo(
			CategoryTitle.NETWORK.get());
	}

	@Test
	void 인터뷰_카테고리가_담긴_퀴즈_Response_dto_로부터_Quiz_엔티티로_변환한다() {
		Category category = Category.builder()
			.categoryTitle(CategoryTitle.INTERVIEW.get())
			.build();

		quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(responseJsonFromOPENAIAPI,
			CategoryTitle.INTERVIEW);

		List<Quiz> quizzes = QuizTransformer.toQuizzes(quizDtoFromResponses, category);

		assertThat(quizzes.get(0).getQuizChoices().size()).isEqualTo(4);
		assertThat(quizzes.get(0).getCategory().getCategoryTitle()).isEqualTo(
			CategoryTitle.INTERVIEW.get());
	}

}
