package quiz.model.category;


import lombok.Getter;
import quiz.model.common.CategoryName;
import quiz.model.quiz.Quiz;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(nullable = false, name = "category_name")
    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

    @OneToMany(mappedBy = "category")
    private List<Quiz> quizzes;

    public String getCategoryName(){
        return categoryName.get();
    }
}
