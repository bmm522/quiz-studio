package quiz.domain.customQuizChoice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import quiz.domain.customQuiz.CustomQuiz;
import quiz.domain.quiz.Quiz;

@Entity
@Getter
@Table(name = "custom_quizChoice")
public class CustomQuizChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "choice_id")
    private long choiceId;

    @Column(nullable = false, name = "choice_content")
    private String choiceContent;

    @Column(nullable = false, name = "is_answer")
    private boolean isAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="quiz_id")
    private CustomQuiz quiz;
}
