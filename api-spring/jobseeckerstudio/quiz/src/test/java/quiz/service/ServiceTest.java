package quiz.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import quiz.domain.category.repository.CategoryRepository;
import quiz.service.category.CategoryService;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

	@Mock
	CategoryRepository categoryRepository;

	@InjectMocks
	CategoryService categoryService;

}
