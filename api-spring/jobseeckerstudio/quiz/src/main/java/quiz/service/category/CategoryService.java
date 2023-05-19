package quiz.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz.domain.category.Category;
import quiz.domain.category.mapper.CategoryMapper;
import quiz.domain.category.repository.CategoryRepository;
import quiz.global.exception.DuplicateTitleException;
import quiz.global.exception.NotFoundEntityException;
import quiz.service.category.dto.S_CategoryGetResponse;
import quiz.service.category.dto.S_CategorySaveRequest;
import quiz.service.category.dto.S_CategorySaveResponse;
import quiz.service.category.dto.S_CategoryUpdateRequest;
import quiz.service.category.dto.S_CategoryUpdateResponse;
import quiz.service.category.mapper.S_CategoryMapper;
import quiz.service.util.PermissionValidator;

@Service
@RequiredArgsConstructor
public class CategoryService {


	private final CategoryRepository categoryRepository;

	@Transactional
	public S_CategorySaveResponse save(final S_CategorySaveRequest request) {
		validateDuplicateTitle(request.getUserKey(), request.getTitle());
		final Category category = CategoryMapper.toEntityWhenSave(request);
		return S_CategoryMapper.toSaveResponse(categoryRepository.save(category));
	}

	private void validateDuplicateTitle(final String userKey, final String title) {
		if (categoryRepository.findCategoryByUserKeyAndTitle(userKey, title).isPresent()) {
			throw new DuplicateTitleException("중복된 카테고리 제목은 사용할 수 없습니다.");
		}
	}

	@Transactional(readOnly = true)
	public S_CategoryGetResponse get(final String userKey) {
		return S_CategoryMapper.toGetResponse(
			categoryRepository.findCategoryDtosByUserKey(
				userKey));
	}

	@Transactional
	public S_CategoryUpdateResponse update(final S_CategoryUpdateRequest request) {
		Category category = getCategoryFromCategoryId
			(request.getCategoryId());
		PermissionValidator.validatePermissionFromUserKey(
			category.getUserKey(),
			request.getUserKey());
		category.updateCategoryName(request.getUpdateTitle());
		category.updateCategoryDescription(request.getUpdateDescription());
		return S_CategoryMapper.toUpdateResponse(category);
	}

	private Category getCategoryFromCategoryId(final Long categoryId) {
		return categoryRepository.findCategoryByCategoryId(
			categoryId).orElseThrow(() -> new NotFoundEntityException(
			"categoryId로 해당 UserCategory 객체를 찾을 수 없습니다."));
	}

}
