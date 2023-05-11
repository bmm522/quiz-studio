package quiz.domain.customCategory;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import quiz.domain.customQuiz.CustomQuiz;

@Entity
@Table(name = "custom_category")
public class CustomCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;

    @Column(nullable = false, name = "category_title")
    private String title;

    @Column(nullable = true, name = "category_description")
    private String description;

    @Column(nullable = false, name = "category_userKey")
    private String userKey;

    @OneToMany(mappedBy = "category")
    private List<CustomQuiz> quizzes;

}