package com.quizbatch.domain.quiz;


import com.quizbatch.domain.BaseTimeEntity;
import com.quizbatch.domain.category.Category;
import com.quizbatch.domain.quizchoice.QuizChoice;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Quiz extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_id")
	private Long id;

	@Column(nullable = false, name = "quiz_title")
	private String quizTitle;

//	@Column(nullable = true)
//	@Enumerated(EnumType.STRING)
//	private Difficulty difficulty;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<QuizChoice> quizChoices = new ArrayList<>();

	@Builder
	public Quiz(String quizTitle) {
		this.quizTitle = quizTitle;
	}

	public String findCategoryName() {
		return this.category.getCategoryTitle();
	}

	public void addCategory(Category category) {
		if (this.category == null) {
			this.category = category;
		}
	}

	public void addChoice(QuizChoice quizChoice) {
		this.quizChoices.add(quizChoice);
		if (quizChoice.getQuiz() != this) {
			quizChoice.setQuiz(this);
		}
	}

	public void addChoices(List<QuizChoice> quizChoices) {
		this.quizChoices.addAll(quizChoices);
	}


	public void updateQuizTitle(final String updateTitle) {
		this.quizTitle = updateTitle;
	}
}
