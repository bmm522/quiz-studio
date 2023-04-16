package quiz.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import quiz.model.quiz.Quiz;
import quiz.model.quiz.repository.QuizMySqlRepositoryImpl;
import quiz.model.quiz.repository.dto.QuizDto;
import quiz.model.quizChoice.QuizChoice;
import quiz.model.redisQuiz.RedisQuiz;
import quiz.model.redisQuiz.repository.QuizRedisRepository;

import java.util.ArrayList;
import java.util.List;
@Component
public class Init {

    @Autowired
    private QuizRedisRepository quizRedisRepository;

    @Autowired
    private QuizMySqlRepositoryImpl quizMySqlRepository;


    public void initData() {

        List<QuizDto> quizRandomList = quizMySqlRepository.findQuizzes();
        for(QuizDto dto : quizRandomList) {
            System.out.println(dto.getCategoryName());
        }
//        List<RedisQuiz> redisQuizList = new ArrayList<>();
//        for(QuizDto quiz : quizRandomList) {
//            String categoryName = quiz.getCategoryName();
//            System.out.println("----------------");
//            System.out.println(categoryName);
//            System.out.println("----------------");
////            String difficulty = quiz.getDifficulty();
//            String quizTitle = quiz.getQuizTitle();
//
//
////            System.out.println(difficulty);
//            System.out.println(quizTitle);
//
//            List<RedisQuiz.RedisQuizChoices> quizChoicesList = new ArrayList<>();
////            for(String content : quiz.getChoiceContentList()) {
////                String choiceContent = quizChoice.getChoiceContent();
////                boolean choiceIsAnswer = quizChoice.getIsAnswer();
////                RedisQuiz.RedisQuizChoices data = RedisQuiz.RedisQuizChoices.builder()
////                    .choiceContent(choiceContent)
////                    .isAnswer(choiceIsAnswer)
////                    .build();
////
////                quizChoicesList.add(data);
////            }
//
//            RedisQuiz data2 = RedisQuiz.builder()
////                .id(categoryName+"_"+difficulty)
//                .quizTitle(quizTitle)
//                .quizChoicesList(quizChoicesList)
//                .build();
//
//            redisQuizList.add(data2);
//        }
//
//       quizRedisRepository.saveAll(redisQuizList);

    }
}
