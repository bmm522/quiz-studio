package quiz.repository.category;

import java.util.List;
import java.util.Optional;
import quiz.domain.category.Category;
import quiz.repository.category.dto.CategoryQueryDto;

public interface CategoryQueryRepository {

	List<CategoryQueryDto> findCategoryDtosByUserKey(final String userKey);

	Optional<Category> findCategoryByCategoryId(final Long categoryId);

	Optional<Category> findCategoryByUserKeyAndTitle(final String userKey, final String title);
}
