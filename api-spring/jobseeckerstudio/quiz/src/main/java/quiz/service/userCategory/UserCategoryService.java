package quiz.service.usercategory;

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
import quiz.service.usercategory.dto.S_UserCategoryGetResponse;
import quiz.service.usercategory.dto.S_UserCategorySaveRequest;
import quiz.service.usercategory.dto.S_UserCategorySaveResponse;
import quiz.service.usercategory.dto.S_UserCategoryUpdateRequest;
import quiz.service.usercategory.dto.S_UserCategoryUpdateResponse;
import quiz.service.usercategory.mapper.S_UserCategoryMapper;

@Service
@RequiredArgsConstructor
public class UserCategoryService {


	private final CategoryRepository categoryRepository;

	@Transactional
	public S_UserCategorySaveResponse save(final S_UserCategorySaveRequest request) {
		validateDuplicateTitle(request.getUserKey(), request.getTitle());
		final Category category = CategoryMapper.toEntityWhenSave(request);
		final Category savedData = categoryRepository.save(category);
		return S_UserCategoryMapper.toSaveResponse(savedData);
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
	public S_UserCategoryGetResponse get(final String userKey) {
		return S_UserCategoryMapper.toGetResponse(
			categoryRepository.findCategoryDtosByUserKey(
				userKey));
	}

	@Transactional
	public S_UserCategoryUpdateResponse update(final S_UserCategoryUpdateRequest request) {
		Category category = getCategory(request);
		validatePermission(category.getUserKey(), request.getUserKey());
		category.updateCategoryName(request.getUpdateTitle());
		category.updateCategoryDescription(request.getUpdateDescription());
		return S_UserCategoryMapper.toUpdateResponse(category);
	}

	private Category getCategory(final S_UserCategoryUpdateRequest request) {
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
