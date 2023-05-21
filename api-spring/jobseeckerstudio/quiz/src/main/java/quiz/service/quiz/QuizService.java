package quiz.service.quiz;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz.domain.category.Category;
import quiz.domain.category.repository.CategoryRepository;
import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.repository.QuizRepository;
import quiz.global.exception.NotFoundEntityException;
import quiz.service.quiz.dto.QuizGetResponse;
import quiz.service.quiz.dto.QuizSaveParam;
import quiz.service.quiz.mapper.QuizMapper;
import quiz.service.util.PermissionValidator;

@RequiredArgsConstructor
@Service
public class QuizService {


	private final QuizRepository quizRepository;
	private final CategoryRepository categoryRepository;


	@Transactional
	public QuizSaveParam.Response saveAll(final QuizSaveParam.Request request) {
		final Category category = getCategoryFromCategoryId
			(request.getCategoryId());
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
				() -> new NotFoundEntityException("categoryId로 해당 UserCategory 객체를 찾을 수 없습니다."));
	}


	@Transactional(readOnly = true)
	public QuizGetResponse get(final String userKey, final Long categoryId) {
		return QuizMapper.toGetResponse(
			quizRepository.findQuizQueryDtoListByCategoryIdAndUserKey(userKey, categoryId));
	}


}
