package quiz.domain.quiz.mapper;

import java.util.List;
import java.util.stream.Collectors;
import quiz.domain.quiz.Quiz;
import quiz.domain.quizChoice.QuizChoice;
import quiz.global.dto.CustomQuizDto;
import quiz.repository.quiz.dto.QuizQueryDto;

public class QuizMapper {


	public static List<QuizQueryDto> toQuizQueryDtoList(List<Quiz> quizList) {

		return quizList.stream().map(quiz -> {
			List<QuizQueryDto.ChoiceDto> choiceDtoList = quiz.getQuizChoices().stream()
				.map(choice -> new QuizQueryDto.ChoiceDto(
					choice.getChoiceId(),
					choice.getChoiceContent(),
					choice.getIsAnswer()))
				.collect(Collectors.toList());

			return QuizQueryDto.builder()
				.quizId(quiz.getId())
				.categoryTitle(quiz.findCategoryName())
				.quizTitle(quiz.getQuizTitle())
				.choiceDtos(choiceDtoList)
				.build();

		}).collect(Collectors.toList());
	}

	public static List<Quiz> toEntitiesWhenSave(final List<CustomQuizDto> customQuizDtos) {
		return customQuizDtos.stream()
			.map(quizDto -> {
				final Quiz quiz = createQuizFromRequest(quizDto);
				addChoicesToQuiz(quiz, quizDto.getChoices());
				return quiz;
			})
			.collect(Collectors.toList());
	}

	private static Quiz createQuizFromRequest(final CustomQuizDto quizDto) {
		return Quiz.builder()
			.quizTitle(quizDto.getTitle())
			.build();
	}

	private static void addChoicesToQuiz(final Quiz quiz,
		final List<CustomQuizDto.Choice> choices) {
		choices.stream()
			.map(QuizMapper::createQuizChoiceFromChoiceDto)
			.forEach(quiz::addChoice);
	}

	private static QuizChoice createQuizChoiceFromChoiceDto(final CustomQuizDto.Choice choice) {
		return QuizChoice.builder()
			.choiceContent(choice.getContent())
			.isAnswer(choice.getIsAnswer())
			.build();
	}
}
