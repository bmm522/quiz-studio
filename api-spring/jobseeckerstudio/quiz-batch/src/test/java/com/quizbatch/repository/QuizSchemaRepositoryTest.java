//package com.quizbatch.repository;
//
//
//import com.quizbatch.domain.category.CategoryRepository;
//import com.quizbatch.domain.quiz.QuizRedisRepository;
//import com.quizbatch.domain.quiz.QuizRepository;
//import com.quizbatch.domain.quizschema.QuizSchema;
//import com.quizbatch.domain.quizschema.QuizSchemaMapper;
//import com.quizbatch.tasklets.makejob.step2converter.QuizDtoConverter;
//import com.quizbatch.tasklets.makejob.step2converter.QuizDtoFromResponse;
//import com.quizbatch.tasklets.makejob.step3mapper.QuizMapper;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.test.context.SpringBootTest;
//import quiz.domain.category.Category;
//import quiz.domain.quiz.Quiz;
//import quiz.repository.quiz.dto.QuizQueryDto;
//
//@SpringBootTest
//@EntityScan(basePackages = {"quiz.domain"})
//public class QuizSchemaRepositoryTest {
//
//	@Autowired
//	private QuizRepository quizRepository;
//
//	@Autowired
//	private CategoryRepository categoryRepository;
//	@Autowired
//	private QuizRedisRepository quizRedisRepository;
//
//	@Test
//	void 데이터_레디스_저장_테스트() {
//		String responseJson = "[{\"title\": \"자바에서 break와 continue의 차이점은 무엇인가요?\", \"choices\": [{\"content\": \"break는 반복문 전체를 종료시키지만, continue는 현재 반복을 종료하고 다음 반복을 실행합니다.\", \"isAnswer\": false},{\"content\": \"break는 반복문 내부에서 다음 문장을 실행하지 않고 반복을 종료시키지만, continue는 현재 반복을 종료하고 바로 다음 반복을 실행합니다.\", \"isAnswer\": true},{\"content\": \"break와 continue의 차이점은 없습니다.\", \"isAnswer\": false},{\"content\": \"break와 continue 모두 반복문을 종료시킵니다.\", \"isAnswer\": false} ]},{\"title\": \"자바에서 클래스와 인스턴스의 차이점은 무엇인가요?\", \"choices\": [{\"content\": \"클래스는 객체를 만들기 위한 설계도이고, 인스턴스는 클래스를 실체화한 객체입니다.\", \"isAnswer\": true},{\"content\": \"클래스와 인스턴스는 전혀 다른 개념이며, 상호 연관성이 없습니다.\", \"isAnswer\": false},{\"content\": \"클래스는 일련의 메서드와 변수의 집합이고, 인스턴스는 이 클래스의 인스턴스를 의미합니다.\", \"isAnswer\": false},{\"content\": \"클래스와 인스턴스는 개념이 아닌, 스태틱과 논-스태틱 개념으로 이해되어야 합니다.\", \"isAnswer\": false} ]}]";
//
//		List<QuizDtoFromResponse> quizDtoFromResponses = QuizDtoConverter.toQuizDtosFromResponses(
//			responseJson);
//		Category category = categoryRepository.findCategoryByCategoryTitle("java");
//		List<Quiz> quizzes = QuizMapper.toQuizzes(quizDtoFromResponses, category);
//
//		List<Quiz> quizList = quizRepository.saveAll(quizzes);
//
//		List<QuizQueryDto> quizQueryDtos = quizRepository.findQuizzesFroRedis();
//
//		List<QuizSchema> quizSchemas = QuizSchemaMapper.toQuizSchemas(quizQueryDtos);
//
//		quizRedisRepository.saveAll(quizSchemas);
////		List<QuizSchema> quizSchemas = QuizDtoConverter.toQuizzes(responseJson);
//
////		quizRedisRepository.saveAll(quizSchemas);
//	}
//}
