package com.quizbatch.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoConverter;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoFromResponse;
import com.quizbatch.tasklets.makequiz.step3mapper.QuizTransformer;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import quiz.domain.category.Category;
import quiz.domain.category.repository.CategoryRepository;
import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.repository.QuizRepository;

@DataJpaTest

public class QuizRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private QuizRepository quizRepository;

	@Test
	void BATCH_저장_테스트() {
		String responseJson = "[{\"title\": \"자바에서 break와 continue의 차이점은 무엇인가요?\", \"choices\": [{\"content\": \"break는 반복문 전체를 종료시키지만, continue는 현재 반복을 종료하고 다음 반복을 실행합니다.\", \"isAnswer\": false},{\"content\": \"break는 반복문 내부에서 다음 문장을 실행하지 않고 반복을 종료시키지만, continue는 현재 반복을 종료하고 바로 다음 반복을 실행합니다.\", \"isAnswer\": true},{\"content\": \"break와 continue의 차이점은 없습니다.\", \"isAnswer\": false},{\"content\": \"break와 continue 모두 반복문을 종료시킵니다.\", \"isAnswer\": false} ]},{\"title\": \"자바에서 클래스와 인스턴스의 차이점은 무엇인가요?\", \"choices\": [{\"content\": \"클래스는 객체를 만들기 위한 설계도이고, 인스턴스는 클래스를 실체화한 객체입니다.\", \"isAnswer\": true},{\"content\": \"클래스와 인스턴스는 전혀 다른 개념이며, 상호 연관성이 없습니다.\", \"isAnswer\": false},{\"content\": \"클래스는 일련의 메서드와 변수의 집합이고, 인스턴스는 이 클래스의 인스턴스를 의미합니다.\", \"isAnswer\": false},{\"content\": \"클래스와 인스턴스는 개념이 아닌, 스태틱과 논-스태틱 개념으로 이해되어야 합니다.\", \"isAnswer\": false} ]}]";
		Category category = categoryRepository.findCategoryByCategoryTitle("java");
		List<QuizDtoFromResponse> quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(
			responseJson, CategoryTitle.JAVA);
		List<Quiz> quizzes = QuizTransformer.toQuizzes(quizDtoFromResponses, category);

		List<Quiz> quizList = quizRepository.saveAll(quizzes);

		assertThat(quizList.size()).isEqualTo(2);
	}
}
