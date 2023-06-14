package quiz.service.quiz;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quiz.controller.quiz.dto.QuizGetCondition;
import quiz.domain.category.Category;
import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.repository.QuizRepository;
import quiz.repository.quiz.dto.QuizQueryDto;
import quiz.service.quiz.dto.QuizGetResponse;
import quiz.service.quiz.dto.QuizGetWithoutPagingResponse;
import quiz.service.quiz.dto.QuizSaveParam;
import quiz.service.quiz.dto.QuizUpdateParam;
import quiz.service.quiz.mapper.ServiceQuizMapper;

@RequiredArgsConstructor
@Service
public class QuizService {

	private final QuizRepository quizRepository;

	/**
	 * 퀴즈를 일괄 저장하고 저장된 퀴즈의 정보를 반환하는 메서드입니다.
	 *
	 * @param request  퀴즈 저장 요청 정보
	 * @param category 퀴즈의 카테고리 정보
	 * @return 퀴즈 저장 결과
	 */
	public QuizSaveParam.Response saveAll(final QuizSaveParam.Request request,
		final Category category) {
		List<Quiz> quizzes = quiz.domain.quiz.mapper.QuizMapper.toEntitiesWhenSave(
			request.getQuizzes());
		quizzes.forEach(quiz -> quiz.addCategory(category));
		return ServiceQuizMapper.toSaveResponse(request.getUserKey(),
			quizRepository.saveAll(quizzes));
	}


	/**
	 * 사용자 키와 카테고리 ID에 해당하는 퀴즈를 페이징하여 조회하는 메서드입니다.
	 *
	 * @param condition 사용자 키와 카테고리 ID를 포함한 조건 정보
	 * @return 퀴즈 조회 결과
	 */

	public QuizGetResponse getQuizzesWithPaging(final QuizGetCondition condition) {
		final List<QuizQueryDto> quizQueryDtoList = quizRepository.findQuizQueryDtoListByIdAndUserKey(
			condition.getUserKey(),
			condition.getCategoryId(),
			condition.getOffset(),
			condition.getPageSize()
		);
		final Long quizTotalCount = quizRepository.getQuizTotalCount(condition.getUserKey(),
			condition.getCategoryId());
		return ServiceQuizMapper.toGetResponse(quizQueryDtoList, quizTotalCount);
	}

	/**
	 * 사용자 키와 카테고리 ID에 해당하는 퀴즈를 페이징 없이 조회하는 메서드입니다.
	 *
	 * @param userKey    사용자 키
	 * @param categoryId 카테고리 ID
	 * @return 퀴즈 조회 결과
	 */
	public QuizGetWithoutPagingResponse getQuizzesWhenTakeQuiz(final String userKey,
		final long categoryId) {
		final List<QuizQueryDto> quizQueryDtoList = quizRepository.findQuizQueryDtoListByCategoryIdAndUserKeyWithOutPaging(
			userKey, categoryId);
		return ServiceQuizMapper.toGetWithOutPagingResponse(quizQueryDtoList);
	}

	/**
	 * 퀴즈의 정보를 업데이트하는 메서드입니다.
	 *
	 * @param request 퀴즈 업데이트 요청 정보
	 * @return 업데이트된 퀴즈 개수
	 */
	public int update(final QuizUpdateParam.Request request) {
		return quizRepository.updateAllTitleByCustomQuizDto(
			request.getQuizzes());

	}


}
