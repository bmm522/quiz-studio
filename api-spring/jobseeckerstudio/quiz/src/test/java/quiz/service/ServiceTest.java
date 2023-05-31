package quiz.service;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import quiz.domain.category.repository.CategoryRepository;
import quiz.domain.quiz.repository.QuizRepository;
import quiz.domain.quizChoice.repository.QuizChoiceRepository;
import quiz.service.category.CategoryService;
import quiz.service.quiz.QuizService;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {


	CategoryRepository categoryRepository;


	QuizRepository quizRepository;

	CategoryService categoryService;

	QuizChoiceRepository quizChoiceRepository;


	QuizService quizService;

	public void setUp() {
		categoryRepository = mock(CategoryRepository.class);
		quizRepository = mock(QuizRepository.class);
		quizChoiceRepository = mock(QuizChoiceRepository.class);

		categoryService = new CategoryService(categoryRepository);
		quizService = new QuizService(quizRepository, quizChoiceRepository, categoryRepository);
	}

}
