package quiz.service.category;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz.domain.category.Category;
import quiz.domain.category.mapper.CategoryMapper;
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

	/**
	 * 카테고리를 저장하는 메서드입니다.
	 *
	 * @param request 카테고리 저장 요청 객체
	 * @return 카테고리 저장 응답 객체
	 */
	@Transactional
	public CategorySaveParam.Response save(final CategorySaveParam.Request request) {
		validateDuplicateTitle(request.getUserKey(), request.getTitle());
		final Category category = CategoryMapper.toEntityWhenSave(
			request);
		return ServiceCategoryMapper.toSaveResponse(categoryRepository.save(category));
	}

	private void validateDuplicateTitle(final String userKey, final String title) {
		if (categoryRepository.findCategoryByUserKeyAndTitle(userKey, title).isPresent()) {
			throw new DuplicateTitleException("중복된 카테고리 제목은 사용할 수 없습니다.");
		}
	}

	/**
	 * 카테고리를 조회하는 메서드입니다.
	 *
	 * @param userKey 사용자 키
	 * @param page    페이지 번호
	 * @return 카테고리 조회 응답 객체
	 */
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

	/**
	 * 카테고리를 옵션으로 조회하는 메서드입니다.
	 *
	 * @param userKey 사용자 키
	 * @return 카테고리 옵션 조회 결과
	 */
	public List<CategoryQueryDto> getCategoriesWhenSelectOption(final String userKey) {
		return categoryRepository.findCategoryDtosByUserKeyWhenSelectOption(userKey);
	}

	/**
	 * 카테고리를 업데이트하는 메서드입니다.
	 *
	 * @param request 카테고리 업데이트 요청 객체
	 * @return 카테고리 업데이트 응답 객체
	 */
	@Transactional
	public CategoryUpdateParam.Response update(final CategoryUpdateParam.Request request) {
		final Category category = getCategoryFromCategoryId(request.getCategoryId());
		PermissionValidator.validatePermissionFromUserKey(category.getUserKey(),
			request.getUserKey());
		category.updateCategoryName(request.getUpdateTitle());
		category.updateCategoryDescription(request.getUpdateDescription());
		return ServiceCategoryMapper.toUpdateResponse(category);
	}

	private Category getCategoryFromCategoryId(final Long categoryId) {
		return categoryRepository.findCategoryByCategoryId(categoryId)
			.orElseThrow(() -> new NotFoundEntityException("userKey로 해당 Category 객체를 찾을 수 없습니다."));
	}

}
