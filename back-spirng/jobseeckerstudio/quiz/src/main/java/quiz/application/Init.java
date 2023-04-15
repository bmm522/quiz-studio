package quiz.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import quiz.model.quiz.Quiz;
import quiz.model.quiz.repository.QuizMySqlRepository;
import quiz.model.quizChoice.QuizChoice;
import quiz.model.redisQuiz.RedisQuiz;
import quiz.model.redisQuiz.repository.QuizRedisRepository;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class Init {

    private final QuizRedisRepository quizRedisRepository;

    private final QuizMySqlRepository quizMySqlRepository;


    public void initData() {

        List<Quiz> quizRandomList = quizMySqlRepository.findAll();
        List<RedisQuiz> redisQuizList = new ArrayList<>();
        for(Quiz quiz : quizRandomList) {
            String categoryName = quiz.getCategory().getCategoryName();
            String difficulty = quiz.getDifficulty().get();
            String quizTitle = quiz.getQuizTitle();
            List<QuizChoice> quizChoices = quiz.getQuizChoices();
            List<RedisQuiz.RedisQuizChoices> quizChoicesList = new ArrayList<>();
            for(QuizChoice quizChoice : quizChoices) {
                String choiceContent = quizChoice.getChoiceContent();
                boolean choiceIsAnswer = quizChoice.getIsAnswer();
                RedisQuiz.RedisQuizChoices data = RedisQuiz.RedisQuizChoices.builder()
                    .choiceContent(choiceContent)
                    .isAnswer(choiceIsAnswer)
                    .build();

                quizChoicesList.add(data);
            }

            RedisQuiz data2 = RedisQuiz.builder()
                .difficulty(difficulty)
                .categoryName(categoryName)
                .quizTitle(quizTitle)
                .quizChoicesList(quizChoicesList)
                .build();

            redisQuizList.add(data2);
        }

       quizRedisRepository.saveAll(redisQuizList);

    }
}
