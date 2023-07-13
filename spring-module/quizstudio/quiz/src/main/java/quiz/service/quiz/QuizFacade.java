package quiz.service.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import quiz.controller.quiz.dto.QuizGetCondition;
import quiz.domain.category.Category;
import quiz.service.category.CategoryService;
import quiz.service.quiz.dto.QuizGetResponse;
import quiz.service.quiz.dto.QuizGetWithoutPagingResponse;
import quiz.service.quiz.dto.QuizSaveParam;
import quiz.service.quiz.dto.QuizUpdateParam;
import quiz.service.quizchoice.QuizChoiceService;
import quiz.service.util.PermissionValidator;

@Component
@RequiredArgsConstructor
public class QuizFacade {


	private final QuizService quizService;

	private final QuizChoiceService quizChoiceService;

	private final CategoryService categoryService;

	/**
	 * 퀴즈를 일괄 저장하고 저장된 퀴즈의 정보를 반환하는 메서드입니다.
	 *
	 * @param request 퀴즈 저장 요청 정보
	 * @return 퀴즈 저장 결과
	 */
	@Transactional
	public QuizSaveParam.Response saveAll(final QuizSaveParam.Request request) {
		final Category category = categoryService.getCategoryFromCategoryId(
			request.getCategoryId());
		PermissionValidator.validatePermissionFromUserKey(request.getUserKey(),
			category.getUserKey());
		return quizService.saveAll(request, category);
	}

	/**
	 * 사용자 키와 카테고리 ID에 기반하여 페이징된 퀴즈를 조회합니다.
	 *
	 * @param condition 사용자 키와 카테고리 ID를 포함한 조건
	 * @return 페이징된 퀴즈 응답
	 */
	@Transactional(readOnly = true)
	public QuizGetResponse getQuizzesWithPaging(final QuizGetCondition condition) {
		return quizService.getQuizzesWithPaging(condition);
	}


	/**
	 * 퀴즈를 조회할 때 페이징 없이 퀴즈를 조회합니다.
	 *
	 * @param userKey    사용자 키
	 * @param categoryId 카테고리 ID
	 * @return 페이징 없는 퀴즈 응답
	 */
	@Transactional(readOnly = true)
	public QuizGetWithoutPagingResponse getQuizzesWhenTakeQuiz(final String userKey,
		final long categoryId) {
		return quizService.getQuizzesWhenTakeQuiz(userKey, categoryId);
	}

	/**
	 * 주어진 퀴즈 업데이트 요청에 따라 퀴즈를 업데이트합니다.
	 *
	 * @param request 퀴즈 업데이트 요청 정보
	 * @return 업데이트된 퀴즈와 선택지의 총 개수
	 */
	@Transactional
	public int update(final QuizUpdateParam.Request request) {
		final Category category = categoryService.getCategoryFromCategoryId(
			request.getCategoryId());
		PermissionValidator.validatePermissionFromUserKey(request.getUserKey(),
			category.getUserKey());
		final int quizUpdateCnt = quizService.updateAllTitle(request);
		final int quizChoiceUpdateCnt = quizChoiceService.updateWhenQuizUpdate(request);
		return quizUpdateCnt + quizChoiceUpdateCnt;
	}
}
