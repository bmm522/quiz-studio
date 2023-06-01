package quiz.domain.quiz.mapper;

import java.util.List;
import java.util.stream.Collectors;
import quiz.domain.quiz.Quiz;
import quiz.domain.quizChoice.QuizChoice;
import quiz.global.dto.CustomQuizDto;
import quiz.repository.quiz.dto.QuizQueryDto;

public class QuizMapper {

	/**
	 * 퀴즈 목록을 퀴즈 조회 DTO로 변환합니다.
	 *
	 * @param quizList 퀴즈 목록
	 * @return 퀴즈 조회 DTO 목록
	 */
	public static List<QuizQueryDto> toQuizQueryDtoList(final List<Quiz> quizList) {
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

	/**
	 * 퀴즈 저장 요청 데이터를 엔티티로 변환합니다.
	 *
	 * @param customQuizDtos 퀴즈 저장 요청 데이터 목록
	 * @return 퀴즈 엔티티 목록
	 */
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
