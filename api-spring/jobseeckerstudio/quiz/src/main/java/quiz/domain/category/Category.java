package quiz.domain.category;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import quiz.domain.quiz.Quiz;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(nullable = false, name = "category_name")
    private String categoryName;

    @Column(nullable = true, name = "category_description")
    private String categoryDescription;

    @OneToMany(mappedBy = "category")
    private List<Quiz> quizzes;

    public void updateCategoryName(String categoryName) {
        if (categoryName != null && !categoryName.isEmpty()) {
            this.categoryName = categoryName;
        }
    }

    public void updateCategoryDescription(String categoryDescription) {
        if(categoryDescription != null && !categoryDescription.isEmpty()) {
            this.categoryDescription = categoryDescription;
        }
    }

}