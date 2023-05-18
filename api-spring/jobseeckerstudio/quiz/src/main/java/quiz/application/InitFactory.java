package quiz.application;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import quiz.domain.quizCache.QuizCache;
import quiz.domain.quizCache.mapper.QuizCacheTransformer;
import quiz.repository.quiz.dto.QuizQueryDto;

@Component
public class InitFactory {


	@Autowired
	private QuizMysqlService quizMysqlService;

	@Autowired
	private quiz.application.QuizCache quizCache;


	public void initData() {
		List<QuizQueryDto> quizQueryDtoList = quizMysqlService.getAllForRedis();
		List<QuizCache> quizList = QuizCacheTransformer.toQuizCacheList(quizQueryDtoList);
		quizCache.deleteAll();
		quizCache.saveAll(quizList);
	}
}
