import { QuizParams } from '../../controller/quiz/dto/QuizParams';
import { QuizServiceMapper } from './mapper/QuizServiceMapper';
import { QuizListItem } from './dto/QuizListItem';
import { NotFoundEntityError } from '../../error/NotFoundEntityError';
import { QuizResponse } from './dto/QuizResponse';
import { Inject, Service } from 'typedi';
import { QuizQueryRepository } from '../../repository/quiz/QuizQueryRepository';
import { QuizQueryRedisRepository } from '../../repository/quiz/QuizQueryRedisRepository';

@Service()
export class QuizService {
  constructor(
    @Inject(() => QuizQueryRedisRepository)
    private readonly quizQueryCacheRepository: QuizQueryRepository,
  ) {}

  async getQuizList(params: QuizParams): Promise<QuizResponse[]> {
    const item = await this.toQuizListRequest(params);
    return await this.getQuizRandomList(item);
  }

  private async getQuizRandomList(item: QuizListItem): Promise<QuizResponse[]> {
    const quizList = await this.quizQueryCacheRepository.findByCategoryNameAndDifficulty(
      item.category as string,
      item.level as string,
    );

    if (quizList.length === 0) {
      throw new NotFoundEntityError('해당 퀴즈 랜덤 리스트를 찾을 수 없습니다.');
    }

    return quizList;
  }

  private async toQuizListRequest(params: QuizParams): Promise<QuizListItem> {
    return QuizServiceMapper.toQuizListRequest(params);
  }
}
