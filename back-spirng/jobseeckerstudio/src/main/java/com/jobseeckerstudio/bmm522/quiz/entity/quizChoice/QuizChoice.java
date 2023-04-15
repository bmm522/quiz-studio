package com.jobseeckerstudio.bmm522.quiz.entity.quizChoice;

import com.jobseeckerstudio.bmm522.quiz.entity.quiz.Quiz;

import javax.persistence.*;


@Entity
public class QuizChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long choiceId;

    @Column(nullable = false)
    private String choiceContent;

    @Column(nullable = false)
    private boolean isAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="quizId")
    private Quiz quiz;
}
