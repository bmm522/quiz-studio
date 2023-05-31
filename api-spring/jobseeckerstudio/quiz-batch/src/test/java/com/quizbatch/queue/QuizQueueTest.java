package com.quizbatch.queue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.quizbatch.domain.category.Category;
import com.quizbatch.domain.quiz.Quiz;
import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoConverter;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoFromResponse;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoFromResponseQueue;
import com.quizbatch.tasklets.makequiz.step3mapper.QuizQueue;
import com.quizbatch.tasklets.makequiz.step3mapper.QuizTransformer;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class QuizQueueTest {


	Category category;

	Category category2;

	Category category3;

	@BeforeEach
	void init() {

		category = Category.builder()
			.categoryTitle("java")
			.build();

		category2 = Category.builder()
			.categoryTitle("datastructure")
			.build();

		category3 = Category.builder()
			.categoryTitle("database")
			.build();
	}

	@Test
	void Queue_크기_테스트() {
		String javaData = "[{\"title\": \"자바의 기본 자료형 중 실수형은?\", \"choices\": [{\"content\": \"int\", \"isAnswer\": false},{\"content\": \"double\", \"isAnswer\": true},{\"content\": \"boolean\", \"isAnswer\": false},{\"content\": \"char\", \"isAnswer\": false} ]},{\"title\": \"자바에서 객체를 생성하기 위해 사용하는 키워드는?\", \"choices\": [{\"content\": \"new\", \"isAnswer\": true},{\"content\": \"this\", \"isAnswer\": false},{\"content\": \"super\", \"isAnswer\": false},{\"content\": \"extends\", \"isAnswer\": false} ]},{\"title\": \"자바에서 상속을 구현하기 위해 사용하는 키워드는?\", \"choices\": [{\"content\": \"new\", \"isAnswer\": false},{\"content\": \"this\", \"isAnswer\": false},{\"content\": \"super\", \"isAnswer\": true},{\"content\": \"extends\", \"isAnswer\": false} ]},{\"title\": \"자바에서 제공하는 제어문 중 반복문은?\", \"choices\": [{\"content\": \"if-else\", \"isAnswer\": false},{\"content\": \"for\", \"isAnswer\": true},{\"content\": \"switch\", \"isAnswer\": false},{\"content\": \"try-catch\", \"isAnswer\": false} ]},{\"title\": \"자바에서 예외 처리를 위해 사용하는 키워드는?\", \"choices\": [{\"content\": \"try\", \"isAnswer\": true},{\"content\": \"finally\", \"isAnswer\": false},{\"content\": \"throw\", \"isAnswer\": false},{\"content\": \"throws\", \"isAnswer\": false} ]}]";
		String dataStructureData = "[{\"title\": \"배열과 연결 리스트의 차이점은?\", \"choices\": [{\"content\": \"배열은 크기가 고정되어 있지만, 연결 리스트는 동적으로 크기가 조정될 수 있다.\", \"isAnswer\": true},{\"content\": \"배열은 동적으로 크기가 조정될 수 있지만, 연결 리스트는 크기가 고정되어 있다.\", \"isAnswer\": false},{\"content\": \"배열은 데이터를 삽입, 삭제하기 쉽지만, 연결 리스트는 어렵다.\", \"isAnswer\": false},{\"content\": \"연결 리스트는 데이터를 삽입, 삭제하기 쉽지만, 배열은 어렵다.\", \"isAnswer\": false} ]},{\"title\": \"스택과 큐의 차이점은?\", \"choices\": [{\"content\": \"스택은 후입선출(LIFO) 구조이고, 큐는 선입선출(FIFO) 구조이다.\", \"isAnswer\": true},{\"content\": \"스택은 선입선출(FIFO) 구조이고, 큐는 후입선출(LIFO) 구조이다.\", \"isAnswer\": false},{\"content\": \"스택과 큐는 모두 후입선출(LIFO) 구조이다.\", \"isAnswer\": false},{\"content\": \"스택과 큐는 모두 선입선출(FIFO) 구조이다.\", \"isAnswer\": false} ]},{\"title\": \"해시 테이블이란 무엇인가?\", \"choices\": [{\"content\": \"해시 함수를 사용하여 데이터를 저장하고 검색하는 자료구조이다.\", \"isAnswer\": true},{\"content\": \"배열과 연결 리스트를 합쳐 놓은 자료구조이다.\", \"isAnswer\": false},{\"content\": \"데이터를 키와 값의 쌍으로 저장하는 자료구조이다.\", \"isAnswer\": false},{\"content\": \"데이터를 트리 구조로 저장하는 자료구조이다.\", \"isAnswer\": false} ]},{\"title\": \"이진 탐색 트리란 무엇인가?\", \"choices\": [{\"content\": \"모든 노드가 최대 두 개의 자식 노드를 가지며, 왼쪽 자식 노드는 부모 노드보다 작고, 오른쪽 자식 노드는 부모 노드보다 큰 값을 가지는 이진 트리이다.\", \"isAnswer\": true},{\"content\": \"모든 노드가 최대 세 개의 자식 노드를 가지며, 왼쪽 자식 노드는 부모 노드보다 작고, 오른쪽 자식 노드는 부모 노드보다 큰 값을 가지는 이진 트리이다.\", \"isAnswer\": false},{\"content\": \"모든 노드가 최대 두 개의 자식 노드를 가지며, 왼쪽 자식 노드는 부모 노드보다 크고, 오른쪽 자식 노드는 부모 노드보다 작은 값을 가지는 이진 트리이다.\", \"isAnswer\": false},{\"content\": \"모든 노드가 최대 세 개의 자식 노드를 가지며, 왼쪽 자식 노드는 부모 노드보다 크고, 오른쪽 자식 노드는 부모 노드보다 작은 값을 가지는 이진 트리이다.\", \"isAnswer\": false} ]},{\"title\": \"그래프란 무엇인가?\", \"choices\": [{\"content\": \"노드와 노드를 연결하는 간선으로 이루어진 자료구조이다.\", \"isAnswer\": true},{\"content\": \"노드와 노드를 연결하는 선으로 이루어진 자료구조이다.\", \"isAnswer\": false},{\"content\": \"노드와 노드를 연결하는 트리 구조이다.\", \"isAnswer\": false},{\"content\": \"노드와 노드를 연결하는 해시 테이블 구조이다.\", \"isAnswer\": false} ]}]";
		String databaseData = "[{\"title\": \"데이터베이스란 무엇인가요?\", \"choices\": [{\"content\": \"데이터를 저장하는 공간\", \"isAnswer\": false},{\"content\": \"데이터의 집합\", \"isAnswer\": false},{\"content\": \"데이터를 효율적으로 관리하기 위한 시스템\", \"isAnswer\": true},{\"content\": \"데이터를 분석하는 시스템\", \"isAnswer\": false} ]},{\"title\": \"SQL의 약자는 무엇인가요?\", \"choices\": [{\"content\": \"Structured Query Language\", \"isAnswer\": true},{\"content\": \"Structured Question Language\", \"isAnswer\": false},{\"content\": \"Structured Query Logic\", \"isAnswer\": false},{\"content\": \"Structured Question Logic\", \"isAnswer\": false} ]},{\"title\": \"데이터베이스에서 테이블이란 무엇인가요?\", \"choices\": [{\"content\": \"데이터의 묶음\", \"isAnswer\": false},{\"content\": \"데이터가 저장되는 공간\", \"isAnswer\": false},{\"content\": \"데이터를 구성하는 열과 행의 집합\", \"isAnswer\": true},{\"content\": \"데이터를 검색하는 기능\", \"isAnswer\": false} ]},{\"title\": \"관계형 데이터베이스란 무엇인가요?\", \"choices\": [{\"content\": \"데이터를 저장하는 공간\", \"isAnswer\": false},{\"content\": \"데이터의 집합\", \"isAnswer\": false},{\"content\": \"테이블 간의 관계를 이용하여 데이터를 구성하는 데이터베이스\", \"isAnswer\": true},{\"content\": \"데이터를 분석하는 시스템\", \"isAnswer\": false} ]},{\"title\": \"데이터베이스에서 인덱스(Index)란 무엇인가요?\", \"choices\": [{\"content\": \"데이터를 저장하는 공간\", \"isAnswer\": false},{\"content\": \"데이터의 집합\", \"isAnswer\": false},{\"content\": \"데이터를 빠르게 검색하기 위한 데이터 구조\", \"isAnswer\": true},{\"content\": \"데이터를 분석하는 시스템\", \"isAnswer\": false} ]}]";
		QuizDtoFromResponseQueue.add(QuizDtoConverter.toQuizDtosFromResponses(
			javaData, CategoryTitle.JAVA));
		QuizDtoFromResponseQueue.add(QuizDtoConverter.toQuizDtosFromResponses(
			dataStructureData, CategoryTitle.DATA_STRUCTURE));
		QuizDtoFromResponseQueue.add(QuizDtoConverter.toQuizDtosFromResponses(
			databaseData, CategoryTitle.DATABASE));

		List<QuizDtoFromResponse> quizDtoFromResponseList1 = QuizDtoFromResponseQueue.getOne();
		List<QuizDtoFromResponse> quizDtoFromResponseList2 = QuizDtoFromResponseQueue.getOne();
		List<QuizDtoFromResponse> quizDtoFromResponseList3 = QuizDtoFromResponseQueue.getOne();

		List<Quiz> quizzes = QuizTransformer.toQuizzes(quizDtoFromResponseList1, category);
		List<Quiz> quizzes2 = QuizTransformer.toQuizzes(quizDtoFromResponseList2, category2);
		List<Quiz> quizzes3 = QuizTransformer.toQuizzes(quizDtoFromResponseList3, category3);

		QuizQueue.add(quizzes);
		QuizQueue.add(quizzes2);
		QuizQueue.add(quizzes3);

		List<Quiz> quizList = QuizQueue.getAllFromQueue();

		assertThat(quizList.size()).isEqualTo(15);

	}
}
