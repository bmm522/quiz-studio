package quiz.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import quiz.domain.category.repository.CategoryRepository;
import quiz.domain.userCategory.repository.UserCategoryRepository;
import quiz.service.usercategory.UserCategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

	@Mock
	UserCategoryRepository userCategoryRepository;

	@Mock
	CategoryRepository categoryRepository;

	@InjectMocks
	UserCategoryService userCategoryService;

}
