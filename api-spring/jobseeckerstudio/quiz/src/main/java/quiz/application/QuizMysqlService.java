package quiz.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quiz.domain.quiz.repository.QuizRepository;
import quiz.repository.quiz.dto.QuizQueryDto;

@Service
@RequiredArgsConstructor
public class QuizMysqlService {


  private final QuizRepository quizRepository;

  public List<QuizQueryDto> getAllForRedis() {
    return quizRepository.findQuizzesFroRedis();
  }


}
