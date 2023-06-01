package quiz.repository.category;

import java.util.List;
import java.util.Optional;
import quiz.domain.category.Category;
import quiz.repository.category.dto.CategoryQueryDto;

public interface CategoryQueryRepository {

	List<CategoryQueryDto> findCategoryDtosByUserKey(final String userKey, final int offset,
		final int pageSize);

	Optional<Category> findCategoryByCategoryId(final Long categoryId);

	Optional<Category> findCategoryByUserKeyAndTitle(final String userKey, final String title);

	Long getCategoryTotalCount(final String userKey);

	Category findCategoryByCategoryTitle(final String categoryTitle);

	List<CategoryQueryDto> findCategoryDtosByUserKeyWhenSelectOption(final String userKey);
}
