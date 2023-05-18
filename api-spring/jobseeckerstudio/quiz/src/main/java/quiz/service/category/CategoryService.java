package quiz.service.category;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz.domain.category.Category;
import quiz.domain.category.mapper.CategoryMapper;
import quiz.domain.category.repository.CategoryRepository;
import quiz.global.exception.DuplicateTitleException;
import quiz.global.exception.NotFoundEntityException;
import quiz.global.exception.PermissionException;
import quiz.service.category.dto.S_CategoryGetResponse;
import quiz.service.category.dto.S_CategorySaveRequest;
import quiz.service.category.dto.S_CategorySaveResponse;
import quiz.service.category.dto.S_CategoryUpdateRequest;
import quiz.service.category.dto.S_CategoryUpdateResponse;
import quiz.service.category.mapper.S_CategoryMapper;

@Service
@RequiredArgsConstructor
public class CategoryService {


	private final CategoryRepository categoryRepository;

	@Transactional
	public S_CategorySaveResponse save(final S_CategorySaveRequest request) {
		validateDuplicateTitle(request.getUserKey(), request.getTitle());
		final Category category = CategoryMapper.toEntityWhenSave(request);
		final Category savedData = categoryRepository.save(category);
		return S_CategoryMapper.toSaveResponse(savedData);
	}

	private void validateDuplicateTitle(String userKey, String title) {
		Optional<Category> categoryOptional = categoryRepository.findCategoryByUserKeyAndTitle(
			userKey,
			title);
		if (categoryOptional.isPresent()) {
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
		Category category = getCategory(request);
		validatePermission(category.getUserKey(), request.getUserKey());
		category.updateCategoryName(request.getUpdateTitle());
		category.updateCategoryDescription(request.getUpdateDescription());
		return S_CategoryMapper.toUpdateResponse(category);
	}

	private Category getCategory(final S_CategoryUpdateRequest request) {
		return categoryRepository.findCategoryByCategoryId(request.getCategoryId())
			.orElseThrow(() -> new NotFoundEntityException(
				"userKey로 해당 Category 객체를 찾을 수 없습니다."));
	}

	private void validatePermission(String savedUserKey, String requestUserKey) {
		if (!savedUserKey.equals(requestUserKey)) {
			throw new PermissionException("권한이 없는 회원입니다. (userKey 일치하지 않음)");
		}
	}
}
