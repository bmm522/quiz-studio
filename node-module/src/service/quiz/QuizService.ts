import { ControllerGetQuizRequest } from '../../controller/quiz/dto/ControllerGetQuizRequest';
import { ServiceQuizMapper } from './mapper/ServiceQuizMapper';
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

  /**
   * 퀴즈 목록을 조회하는 메서드
   *
   * @param dto 퀴즈 조회 요청 객체
   * @returns 퀴즈 목록 배열 (Promise)
   * @throws NotFoundEntityError - 해당 퀴즈 랜덤 리스트를 찾을 수 없는 경우 예외 발생
   */
  async getQuizList(dto: ControllerGetQuizRequest): Promise<ServiceGetQuizResponse[]> {
    const item = await ServiceQuizMapper.toGetRequest(dto);
    return await this.quizQueryRepository.findByCategoryNameAndDifficulty(
      item.category as string,
      // item.level as string,
    );
  }
}
