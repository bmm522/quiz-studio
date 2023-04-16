import {Inject, Service} from 'typedi';
import { QuizParams } from '../../controller/quiz/dto/QuizParams';
import { QuizServiceMapper } from './mapper/QuizServiceMapper';
import { InjectRepository } from 'typeorm-typedi-extensions';
import { QuizQueryMySQLRepository } from '../../repository/mysql/Quiz/QuizQueryMySQLRepository';
import { Quiz } from '../../entity/quiz/Quiz';
import { QuizListItem } from './dto/QuizListItem';
import { NotFoundEntityError } from '../../error/NotFoundEntityError';
import { QuizResponse } from './dto/QuizResponse';
import {QuizRedisRepository} from "../../repository/redis/Quiz/QuizRedisRepository";

@Service()
export class ReadQuizService {
  constructor(@InjectRepository() private quizQueryMySQLRepository: QuizQueryMySQLRepository,
  private quizRedisRepository: QuizRedisRepository) {}
  async getQuizList(params: QuizParams): Promise<QuizResponse[]> {
    const item = await this.toRequest(params);
    const quizRandomList = await this.getQuizRandomList(item);
    return await this.toResponse(quizRandomList);
  }

  private async getQuizRandomList(item: QuizListItem): Promise<Quiz[]> {
    await this.quizRedisRepository.findByCategoryNameAndDifficulty(
      item.getCategory() as string,
      item.getLevel() as string,
    );
    const quizList = await this.quizQueryMySQLRepository.getQuizRandomList(
        item.getCategory(),
        item.getLevel(),
    );

    if (!quizList) {
      throw new NotFoundEntityError('해당 퀴즈 랜덤 리스트를 찾을 수 없습니다.');
    }

    return quizList;
  }

  private async toRequest(params: QuizParams): Promise<QuizListItem> {
    return QuizServiceMapper.toQuizListRequest(params);
  }

  private async toResponse(quizRandomList: Quiz[]): Promise<QuizResponse[]> {
    return QuizServiceMapper.toQuizResponse(quizRandomList);
  }
}
