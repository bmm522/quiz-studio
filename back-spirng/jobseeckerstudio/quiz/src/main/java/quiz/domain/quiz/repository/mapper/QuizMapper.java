package quiz.domain.quiz.repository.mapper;

import quiz.domain.quiz.Quiz;
import quiz.domain.quiz.repository.dto.QuizDto;

import java.util.List;
import java.util.stream.Collectors;

public class QuizMapper {

    public static List<QuizDto> toQuizDto(List<Quiz> quizList) {
        return quizList.stream().map(quiz -> {
            List<QuizDto.ChoiceDto> choiceDtoList = quiz.getQuizChoices().stream()
                .map(choice -> new QuizDto.ChoiceDto(choice.getChoiceContent(), choice.getIsAnswer()))
                .collect(Collectors.toList());

            return QuizDto.builder()
                .categoryName(quiz.getCategoryName())
                .difficulty(quiz.getDifficulty())
                .quizTitle(quiz.getQuizTitle())
                .choiceDtos(choiceDtoList)
                .build();

        }).collect(Collectors.toList());
    }
}
