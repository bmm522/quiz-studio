package quiz.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quiz.domain.quiz.repository.QuizMySqlRepository;
import quiz.repository.quiz.dto.QuizQueryDto;

@Service
@RequiredArgsConstructor
public class QuizMysqlService {


	private final QuizMySqlRepository quizMySqlRepository;

	public List<QuizQueryDto> getAllForRedis() {
		return quizMySqlRepository.findQuizzes();
	}


}
