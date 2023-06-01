package quiz.service.category;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz.domain.category.Category;
import quiz.domain.category.repository.CategoryRepository;
import quiz.global.exception.DuplicateTitleException;
import quiz.global.exception.NotFoundEntityException;
import quiz.repository.category.dto.CategoryQueryDto;
import quiz.service.category.dto.CategoryGetCondition;
import quiz.service.category.dto.CategoryGetResponse;
import quiz.service.category.dto.CategorySaveParam;
import quiz.service.category.dto.CategoryUpdateParam;
import quiz.service.category.mapper.ServiceCategoryMapper;
import quiz.service.util.PermissionValidator;

@Service
@RequiredArgsConstructor
public class CategoryService {


	private final CategoryRepository categoryRepository;

	@Transactional
	public CategorySaveParam.Response save(final CategorySaveParam.Request request) {
		validateDuplicateTitle(request.getUserKey(), request.getTitle());
		final Category category = quiz.domain.category.mapper.CategoryMapper.toEntityWhenSave(
			request);
		return ServiceCategoryMapper.toSaveResponse(categoryRepository.save(category));
	}

	private void validateDuplicateTitle(final String userKey, final String title) {
		if (categoryRepository.findCategoryByUserKeyAndTitle(userKey, title).isPresent()) {
			throw new DuplicateTitleException("중복된 카테고리 제목은 사용할 수 없습니다.");
		}
	}

	@Transactional(readOnly = true)
	public CategoryGetResponse get(final String userKey, final int page) {
		final CategoryGetCondition item = ServiceCategoryMapper.toGetCondition(userKey, page);
		final List<CategoryQueryDto> categoryQueryDtoList = categoryRepository.findCategoryDtosByUserKey(
			item.getUserKey(),
			item.getOffset(),
			item.getPageSize()
		);
		final Long categoryTotalCount = categoryRepository.getCategoryTotalCount(item.getUserKey());
		return ServiceCategoryMapper.toGetResponse(categoryQueryDtoList, categoryTotalCount);

	}

	public List<CategoryQueryDto> getCategoriesWhenSelectOption(final String userKey) {
		return categoryRepository.findCategoryDtosByUserKeyWhenSelectOption(userKey);
	}

	@Transactional
	public CategoryUpdateParam.Response update(final CategoryUpdateParam.Request request) {
		Category category = getCategoryFromCategoryId
			(request.getCategoryId());
		PermissionValidator.validatePermissionFromUserKey(
			category.getUserKey(),
			request.getUserKey());
		category.updateCategoryName(request.getUpdateTitle());
		category.updateCategoryDescription(request.getUpdateDescription());
		return ServiceCategoryMapper.toUpdateResponse(category);
	}

	private Category getCategoryFromCategoryId(final Long categoryId) {
		return categoryRepository.findCategoryByCategoryId(
			categoryId).orElseThrow(() -> new NotFoundEntityException(
			"userKey로 해당 Category 객체를 찾을 수 없습니다."));
	}


}
