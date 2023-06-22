package com.quizbatch.tasklets.makequiz.step3mapper;

import com.quizbatch.domain.category.Category;
import com.quizbatch.domain.category.CategoryRepository;
import com.quizbatch.domain.quiz.Quiz;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoFromResponse;
import com.quizbatch.tasklets.makequiz.step2converter.QuizDtoFromResponseQueue;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapperTasklet implements Tasklet {

	private final CategoryRepository categoryRepository;


	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
		throws Exception {
		List<QuizDtoFromResponse> quizDtoFromResponses = QuizDtoFromResponseQueue.getOne();
		Category category = categoryRepository.findCategoryByCategoryTitle(
			quizDtoFromResponses.get(0).getCategoryTitle().get());
		List<Quiz> quizzes = QuizTransformer.toQuizzes(quizDtoFromResponses, category);
		QuizQueue.add(quizzes);
		return RepeatStatus.FINISHED;
	}


}
