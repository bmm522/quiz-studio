package quiz.domain.quiz;


import quiz.domain.category.Category;
import quiz.domain.common.Difficulty;
import quiz.domain.quizChoice.QuizChoice;

import javax.persistence.*;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private long quizId;

    @Column(nullable = false, name = "quiz_title")
    private String quizTitle;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER)
    private List<QuizChoice> quizChoices;

    public String getCategoryName() {
        return this.category.getCategoryName();
    }

    public String getQuizTitle() {
        return this.quizTitle;
    }

    public String getDifficulty() {
        return this.difficulty.get();
    }

    public List<QuizChoice> getQuizChoices() {
        return this.quizChoices;
    }
}
