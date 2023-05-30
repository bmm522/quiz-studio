package com.quizbatch.tasklets.makequiz.step3mapper;

import com.quizbatch.domain.category.CategoryQueryRepositoryImpl;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoFromResponse;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoFromResponseQueue;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import quiz.domain.category.Category;
import quiz.domain.quiz.Quiz;

@Component
@RequiredArgsConstructor
public class MapperTasklet implements Tasklet {

	private final CategoryQueryRepositoryImpl categoryQueryRepository;

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
		throws Exception {
		List<QuizDtoFromResponse> quizDtoFromResponses = QuizDtoFromResponseQueue.getOne();
		Category category = categoryQueryRepository.findCategoryByCategoryTitle("java");
		List<Quiz> quizzes = QuizMapper.toQuizzes(quizDtoFromResponses, category);
		QuizQueue.add(quizzes);
		System.out.println(quizzes.size());
		return RepeatStatus.FINISHED;
	}
}
