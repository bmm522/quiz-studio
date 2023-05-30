package com.quizbatch.tasklets.saverdbms.step1save;

import com.quizbatch.domain.quiz.QuizRepository;
import com.quizbatch.tasklets.makequiz.step3mapper.QuizQueue;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import quiz.domain.quiz.Quiz;

@Component
@RequiredArgsConstructor
public class SaveQuizAtRDBMSTasklet implements Tasklet {

	private final QuizRepository quizRepository;

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
		throws Exception {
		List<Quiz> quizzes = QuizQueue.getAllFromQueue();
		quizRepository.saveAll(quizzes);
		return RepeatStatus.FINISHED;
	}
}
