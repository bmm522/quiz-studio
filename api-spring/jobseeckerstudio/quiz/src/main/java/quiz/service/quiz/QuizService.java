package quiz.service.quiz;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.repository.QuizMySqlRepository;
import quiz.domain.quiz.repository.mapper.QuizMapper;
import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.UserCategoryRepository;
import quiz.global.exception.NotFoundEntityException;
import quiz.service.quiz.dto.S_QuizGetResponse;
import quiz.service.quiz.dto.S_QuizSaveRequest;
import quiz.service.quiz.dto.S_QuizSaveResponse;
import quiz.service.quiz.mapper.S_QuizMapper;
import quiz.service.util.PermissionValidator;

@RequiredArgsConstructor
@Service
public class QuizService {


	private final QuizMySqlRepository quizRepository;
	private final UserCategoryRepository userCategoryRepository;

	@Transactional
	public S_QuizSaveResponse saveAll(final S_QuizSaveRequest request) {
		final UserCategory userCategory = getUserCategory(request.getCategoryId());
		PermissionValidator.validatePermissionFromUserKey(request.getUserKey(),
			userCategory.getUserKey());

		final List<Quiz> quizzes = QuizMapper.toEntitiesWhenSave(request.getQuizzes());
		for (Quiz quiz : quizzes) {
			quiz.addCategory(userCategory.getCategory());
		}

		return S_QuizMapper.toSaveResponse(request.getUserKey(), quizRepository.saveAll(quizzes));
	}

	private UserCategory getUserCategory(final long categoryId) {
		return userCategoryRepository.findUserCategoryByCategoryId(
			categoryId).orElseThrow(() -> new NotFoundEntityException(
			"categoryId로 해당 UserCategory 객체를 찾을 수 없습니다."));
	}

	@Transactional(readOnly = true)
	public S_QuizGetResponse get(final String userKey, final long categoryId) {
		return S_QuizMapper.toGetResponse(quizRepository.findQuizDtoByCategoryId(categoryId));
	}
}
