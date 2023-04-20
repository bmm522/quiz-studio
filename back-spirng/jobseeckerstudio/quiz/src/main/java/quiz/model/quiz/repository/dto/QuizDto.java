package quiz.model.quiz.repository.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class QuizDto {
    private final String categoryName;
    private final String difficulty;
    private final String quizTitle;
    private final List<ChoiceDto> choiceDtos;



    @Builder
    public QuizDto(String categoryName, String difficulty, String quizTitle, List<ChoiceDto> choiceDtos) {
        this.categoryName = categoryName;
        this.difficulty = difficulty;
        this.quizTitle = quizTitle;
        this.choiceDtos = choiceDtos;
    }

    @Getter
    public static class ChoiceDto {

        private final String choiceContent;
        private final boolean isAnswer;

        @Builder
        public ChoiceDto(String choiceContent, boolean isAnswer) {
            this.choiceContent= choiceContent;
            this.isAnswer = isAnswer;
        }
    }
}
