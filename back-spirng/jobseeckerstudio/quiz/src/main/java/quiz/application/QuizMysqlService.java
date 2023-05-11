package quiz.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quiz.domain.quiz.repository.QuizMySqlRepository;
import quiz.domain.quiz.repository.dto.QuizDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizMysqlService {


    private final QuizMySqlRepository quizMySqlRepository;

    public List<QuizDto> getAllForRedis() {
        return quizMySqlRepository.findQuizzes();
    }


}
