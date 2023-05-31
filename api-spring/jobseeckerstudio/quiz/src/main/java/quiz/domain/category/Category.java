package quiz.domain.category;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import quiz.domain.BaseTimeEntity;
import quiz.domain.quiz.Quiz;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class Category extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;

	@Column(nullable = false, name = "category_title")
	private String categoryTitle;

	@Column(nullable = true, name = "category_description")
	private String categoryDescription;

	@OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<Quiz> quizzes;

	//	@OneToOne
//	@JoinColumn(referencedColumnName = "user_category_id", nullable = true)
//	private UserCategory userCategory;
	@Column(nullable = true, name = "user_key")
	private String userKey;

	public void updateCategoryName(String categoryTitle) {
		if (categoryTitle != null && !categoryTitle.isEmpty()) {
			this.categoryTitle = categoryTitle;
		}
	}

	public void updateCategoryDescription(String categoryDescription) {
		if (categoryDescription != null && !categoryDescription.isEmpty()) {
			this.categoryDescription = categoryDescription;
		}
	}

}
