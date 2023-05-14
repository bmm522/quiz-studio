package quiz.domain.userCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import quiz.domain.BaseTimeEntity;
import quiz.domain.category.Category;

@Entity
@Table(name = "user_category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserCategory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_category_id")
    private long userCategoryId;


    @JoinColumn(referencedColumnName = "category_id")
    @OneToOne
    private Category category;

    @Column(nullable = false, name = "relation_userKey")
    private String userKey;

    public void addCategory(Category category) {
        if (this.category == null) {
            this.category = category;
        }
    }

    public String getCategoryName() {
        return this.category.getCategoryName();
    }

    public String getCategoryDescription() {
        return this.category.getCategoryDescription();
    }

}