package quiz.domain.customCategory;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import quiz.domain.BaseTimeEntity;
import quiz.domain.customCategory.repository.dto.CustomCategoryDto;
import quiz.domain.customQuiz.CustomQuiz;

@Entity
@Table(name = "custom_category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CustomCategory extends BaseTimeEntity {

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

    public CustomCategoryDto toDto(){
        return CustomCategoryDto.builder()
            .categoryId(this.categoryId)
            .userKey(this.userKey)
            .title(this.title)
            .description(this.description)
            .build();
    }

}