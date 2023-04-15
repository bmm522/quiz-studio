package com.jobseeckerstudio.user.quiz.entity.category;

import com.jobseeckerstudio.user.quiz.entity.common.CategoryName;
import com.jobseeckerstudio.user.quiz.entity.quiz.Quiz;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

    @OneToMany(mappedBy = "category")
    private List<Quiz> quizzes;
}
