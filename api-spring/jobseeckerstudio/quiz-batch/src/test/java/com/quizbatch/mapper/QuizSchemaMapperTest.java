package com.quizbatch.mapper;

import com.quizbatch.domain.quizschema.QuizChoicesSchema;
import com.quizbatch.domain.quizschema.QuizSchema;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoConverter;
import java.util.List;
import org.junit.jupiter.api.Test;

public class QuizSchemaMapperTest {

	@Test
	void QuizMapper_api_response_으로부터_오는_데이터_매핑_테스트() {
		String responseJson = "[{\"quizTitle\": \"자바에서 break와 continue의 차이점은 무엇인가요?\", \"quizChoices\": [{\"choiceContent\": \"break는 반복문 전체를 종료시키지만, continue는 현재 반복을 종료하고 다음 반복을 실행합니다.\", \"isAnswer\": false},{\"choiceContent\": \"break는 반복문 내부에서 다음 문장을 실행하지 않고 반복을 종료시키지만, continue는 현재 반복을 종료하고 바로 다음 반복을 실행합니다.\", \"isAnswer\": true},{\"choiceContent\": \"break와 continue의 차이점은 없습니다.\", \"isAnswer\": false},{\"choiceContent\": \"break와 continue 모두 반복문을 종료시킵니다.\", \"isAnswer\": false} ]},{\"quizTitle\": \"자바에서 클래스와 인스턴스의 차이점은 무엇인가요?\", \"quizChoices\": [{\"choiceContent\": \"클래스는 객체를 만들기 위한 설계도이고, 인스턴스는 클래스를 실체화한 객체입니다.\", \"isAnswer\": true},{\"choiceContent\": \"클래스와 인스턴스는 전혀 다른 개념이며, 상호 연관성이 없습니다.\", \"isAnswer\": false},{\"choiceContent\": \"클래스는 일련의 메서드와 변수의 집합이고, 인스턴스는 이 클래스의 인스턴스를 의미합니다.\", \"isAnswer\": false},{\"choiceContent\": \"클래스와 인스턴스는 개념이 아닌, 스태틱과 논-스태틱 개념으로 이해되어야 합니다.\", \"isAnswer\": false} ]}]\n";

		List<QuizSchema> quizSchemas = QuizDtoConverter.toQuizzes(responseJson);

		for (QuizSchema quizSchema : quizSchemas) {
			System.out.println("QuizSchema Title: " + quizSchema.getQuizTitle());
			System.out.println("QuizSchema Choices: ");
			for (QuizChoicesSchema choice : quizSchema.getQuizChoices()) {
				System.out.println("Choice Content: " + choice.getChoiceContent());
				System.out.println("Is Answer: " + choice.isAnswer());
			}

		}
		System.out.println(quizSchemas.size());
	}
}
