package quiz.service.quiz;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz.controller.quiz.dto.QuizGetCondition;
import quiz.controller.quiz.util.QuizConverter;
import quiz.domain.category.Category;
import quiz.domain.category.repository.CategoryRepository;
import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.repository.QuizRepository;
import quiz.domain.quizChoice.repository.QuizChoiceRepository;
import quiz.global.dto.CustomQuizDto;
import quiz.global.exception.NotFoundEntityException;
import quiz.repository.quiz.dto.QuizQueryDto;
import quiz.service.quiz.dto.QuizGetResponse;
import quiz.service.quiz.dto.QuizGetWithoutPagingResponse;
import quiz.service.quiz.dto.QuizSaveParam;
import quiz.service.quiz.dto.QuizUpdateParam;
import quiz.service.quiz.mapper.QuizMapper;
import quiz.service.util.PermissionValidator;

@RequiredArgsConstructor
@Service
public class QuizService {


	private final QuizRepository quizRepository;
	private final QuizChoiceRepository quizChoiceRepository;
	private final CategoryRepository categoryRepository;


	@Transactional
	public QuizSaveParam.Response saveAll(final QuizSaveParam.Request request) {
		final Category category = getCategoryFromCategoryId(request.getCategoryId());
		PermissionValidator.validatePermissionFromUserKey(request.getUserKey(),
			category.getUserKey());

		List<Quiz> quizzes = quiz.domain.quiz.mapper.QuizMapper.toEntitiesWhenSave(
			request.getQuizzes());
		quizzes.forEach(quiz -> quiz.addCategory(category));

		return QuizMapper.toSaveResponse(request.getUserKey(), quizRepository.saveAll(quizzes));
	}

	private Category getCategoryFromCategoryId(final Long categoryId) {
		return categoryRepository.findCategoryByCategoryId(categoryId)
			.orElseThrow(
				() -> new NotFoundEntityException("categoryId로 해당 Category 객체를 찾을 수 없습니다."));
	}


	@Transactional(readOnly = true)
	public QuizGetResponse getQuizzesWithPaging(final String userKey, final Long categoryId,
		final int page) {
		final QuizGetCondition item = QuizConverter.toGetConition(userKey, categoryId, page);
		final List<QuizQueryDto> quizQueryDtoList = quizRepository.findQuizQueryDtoListByCategoryIdAndUserKey(
			item.getUserKey(),
			item.getCategoryId(),
			item.getOffset(),
			item.getPageSize()
		);
		final Long quizTotalCount = quizRepository.getQuizTotalCount(item.getUserKey(),
			item.getCategoryId());
		return QuizMapper.toGetResponse(quizQueryDtoList, quizTotalCount);

	}

	@Transactional(readOnly = true)
	public QuizGetWithoutPagingResponse getQuizzesWhenTakeQuiz(final String userKey,
		final long categoryId) {
		final List<QuizQueryDto> quizQueryDtoList = quizRepository.findQuizQueryDtoListByCategoryIdAndUserKeyWithOutPaging(
			userKey, categoryId);

		return QuizMapper.toGetWithOutPagingResponse(quizQueryDtoList);
	}


	@Transactional
	public int update(QuizUpdateParam.Request request) {
		final Category category = getCategoryFromCategoryId(request.getCategoryId());
		PermissionValidator.validatePermissionFromUserKey(request.getUserKey(),
			category.getUserKey());

		int quizUpdateResultCnt = quizRepository.updateAllTitleByCustomQuizDto(
			request.getQuizzes());

		List<CustomQuizDto.Choice> quizChoices = new ArrayList<>();

		for (int i = 0; i < request.getQuizzes().size(); i++) {
			List<CustomQuizDto.Choice> temp = request.getQuizChoiceFromIndex(i);
			for (int z = 0; z < 4; z++) {
				quizChoices.add(temp.get(z));
			}
		}
		final int choiceUpdateResultCnt = quizChoiceRepository.updateAllContentAndIsAnswer(
			quizChoices);

		return quizUpdateResultCnt + choiceUpdateResultCnt;

	}


}
