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
import quiz.domain.quiz.Quiz;

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

	@OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<Quiz> quizzes;

	public void updateCategoryName(String categoryName) {
		if (categoryName != null && !categoryName.isEmpty()) {
			this.categoryName = categoryName;
		}
	}

	public void updateCategoryDescription(String categoryDescription) {
		if (categoryDescription != null && !categoryDescription.isEmpty()) {
			this.categoryDescription = categoryDescription;
		}
	}

}
