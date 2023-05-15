// package quiz.domain.customQuiz;
//
// import java.util.List;
//
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
// import javax.persistence.Table;
//
// import quiz.domain.BaseTimeEntity;
// import quiz.domain.category.Category;
// import quiz.domain.customCategory.UserCategory;
// import quiz.domain.customQuizChoice.CustomQuizChoice;
// import quiz.domain.quizChoice.QuizChoice;
//
// @Entity
// @Table(name = "custom_quiz")
// public class CustomQuiz extends BaseTimeEntity {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private long quizId;
//
//     @Column(nullable = false, name = "quiz_title")
//     private String quizTitle;
//
//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name="category_id")
//     private UserCategory category;
//
//     @OneToMany(mappedBy = "quiz")
//     private List<CustomQuizChoice> quizChoices;
// }
