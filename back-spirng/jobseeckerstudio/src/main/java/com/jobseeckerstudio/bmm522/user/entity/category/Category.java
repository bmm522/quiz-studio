package com.jobseeckerstudio.bmm522.user.entity.category;

import com.jobseeckerstudio.bmm522.user.entity.common.CategoryName;
import com.jobseeckerstudio.bmm522.user.entity.quiz.Quiz;

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
