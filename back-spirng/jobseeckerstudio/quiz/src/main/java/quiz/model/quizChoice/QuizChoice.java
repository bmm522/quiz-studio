package quiz.model.quizChoice;

import lombok.Getter;
import quiz.model.quiz.Quiz;

import javax.persistence.*;


@Entity
@Getter
@Table(name = "quiz_choice")
public class QuizChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private long choiceId;

    @Column(nullable = false, name = "choice_content")
    private String choiceContent;

    @Column(nullable = false, name = "is_answer")
    private boolean isAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="quiz_id")
    private Quiz quiz;

    public boolean getIsAnswer() {
        return isAnswer;
    }
}
