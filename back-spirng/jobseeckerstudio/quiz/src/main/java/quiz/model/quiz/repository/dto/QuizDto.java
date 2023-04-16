package quiz.model.quiz.repository.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import quiz.model.common.CategoryName;
import quiz.model.common.Difficulty;

import java.util.List;

@Getter
@Setter
public class QuizDto {
    private String categoryName;
    private String difficulty;
    private String quizTitle;
    private List<ChoiceDto> choiceDtos;



    @Builder
    public QuizDto(String categoryName, String difficulty, String quizTitle, List<ChoiceDto> choiceDtos) {
        this.categoryName = categoryName;
        this.difficulty = difficulty;
        this.quizTitle = quizTitle;
        this.choiceDtos = choiceDtos;
    }

    @Getter
    public static class ChoiceDto {

        private String choiceContent;
        private boolean isAnswer;

        @Builder
        public ChoiceDto(String choiceContent, boolean isAnswer) {
            this.choiceContent= choiceContent;
            this.isAnswer = isAnswer;
        }
    }
}
