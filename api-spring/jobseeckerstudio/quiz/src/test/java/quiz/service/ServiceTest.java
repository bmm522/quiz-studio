package quiz.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import quiz.domain.category.repository.CategoryRepository;
import quiz.domain.quiz.repository.QuizRepository;
import quiz.domain.quizChoice.repository.QuizChoiceRepository;
import quiz.service.category.CategoryService;
import quiz.service.quiz.QuizService;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {


	@Mock
	CategoryRepository categoryRepository;


	@Mock
	QuizRepository quizRepository;

	@InjectMocks
	CategoryService categoryService;

	@Mock
	QuizChoiceRepository quizChoiceRepository;


	@InjectMocks
	QuizService quizService;


}
