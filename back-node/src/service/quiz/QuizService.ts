import { ControllerGetQuizRequest } from '../../controller/quiz/dto/ControllerGetQuizRequest';
import { QuizServiceMapper } from './mapper/QuizServiceMapper';
import { ServiceGetQuizRequest } from './dto/ServiceGetQuizRequest';
import { NotFoundEntityError } from '../../error/NotFoundEntityError';
import { ServiceGetQuizResponse } from './dto/ServiceGetQuizResponse';
import { Inject, Service } from 'typedi';
import { QuizQueryRepository } from '../../repository/quiz/QuizQueryRepository';
import { QuizQueryRedisRepository } from '../../repository/quiz/QuizQueryRedisRepository';

@Service()
export class QuizService {
  constructor(
    @Inject(() => QuizQueryRedisRepository)
    private readonly quizQueryRepository: QuizQueryRepository,
  ) {}

  async getQuizList(dto: ControllerGetQuizRequest): Promise<ServiceGetQuizResponse[]> {
    const item = await QuizServiceMapper.toGetRequest(dto);
    return await this.quizQueryRepository.findByCategoryNameAndDifficulty(
      item.category as string,
      item.level as string,
    );
  }
}
