package com.jobseeckerstudio.user.quiz.entity.quiz;

import com.jobseeckerstudio.user.quiz.entity.category.Category;
import com.jobseeckerstudio.user.quiz.entity.common.Difficulty;
import com.jobseeckerstudio.user.quiz.entity.quizChoice.QuizChoice;

import javax.persistence.*;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private long quizId;

    @Column(nullable = false)
    private String quizTitle;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryId")
    private Category category;

    @OneToMany(mappedBy = "quiz")
    private List<QuizChoice> quizChoices;

}
