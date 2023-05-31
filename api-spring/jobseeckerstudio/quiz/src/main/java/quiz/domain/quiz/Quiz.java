package quiz.domain.quiz;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
import quiz.domain.BaseTimeEntity;
import quiz.domain.category.Category;
import quiz.domain.quizChoice.QuizChoice;
import quiz.global.dto.CustomQuizDto;
import quiz.global.dto.CustomQuizDto.Choice;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Quiz extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_id")
	private long quizId;

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

	public String getCategoryName() {
		return this.category.getCategoryTitle();
	}

	public String getQuizTitle() {
		return this.quizTitle;
	}

//	public String getDifficulty() {
//		return this.difficulty.get();
//	}

	public List<QuizChoice> getQuizChoices() {
		return this.quizChoices;
	}

	@Builder
	public Quiz(String quizTitle) {
		this.quizTitle = quizTitle;
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

	public CustomQuizDto toCustomQuizDto() {
		System.out.println(quizChoices.size());
		List<Choice> choices = quizChoices.stream()
			.map(choice -> Choice.builder()
				.content(choice.getChoiceContent())
				.isAnswer(choice.getIsAnswer())
				.build())
			.collect(Collectors.toList());

		return CustomQuizDto.builder()
			.title(quizTitle)
			.choices(choices)
			.build();
	}

	public void updateQuizTitle(final String updateTitle) {
		this.quizTitle = updateTitle;
	}
}
