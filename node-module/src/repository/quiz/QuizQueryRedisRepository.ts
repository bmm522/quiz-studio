import { Service } from 'typedi';
import Redis from 'ioredis';
import { env } from '../../config/env';
import { ServiceGetQuizResponse } from '../../service/quiz/dto/ServiceGetQuizResponse';
import { once } from 'events';
import { QuizQueryRepository } from './QuizQueryRepository';
import { NotFoundEntityError } from '../../error/NotFoundEntityError';
@Service()
export class QuizQueryRedisRepository implements QuizQueryRepository {
  /**
   * 카테고리 이름과 난이도에 해당하는 퀴즈 목록을 조회하는 메서드
   *
   * @param categoryName 카테고리 이름
   * @returns 퀴즈 목록 배열 (Promise)
   * @throws NotFoundEntityError - 해당 퀴즈 랜덤 리스트를 찾을 수 없는 경우 예외 발생
   */
  async findByCategoryNameAndDifficulty(categoryName: string): Promise<ServiceGetQuizResponse[]> {
    const redisClient = this.createRedisClient();

    try {
      const quizDatas = await this.getQuizData(redisClient, categoryName);
      const randomQuizResponses = this.createRandomQuizResponses(quizDatas);

      if (randomQuizResponses.length === 0) {
        throw new NotFoundEntityError('해당 퀴즈 랜덤 리스트를 찾을 수 없습니다.');
      }

      return randomQuizResponses;
    } catch (err) {
      throw err;
    } finally {
      await redisClient.quit();
    }
  }

  public createRedisClient(): Redis {
    return new Redis({
      host: env.redis.host as string,
      port: parseInt(env.redis.port as string),
    });
  }

  /**
   * Redis에서 퀴즈 데이터를 조회하는 메서드
   *
   * @param redisClient Redis 클라이언트
   * @param categoryName 카테고리 이름
   * @returns 퀴즈 데이터 배열 (Promise)
   */
  public async getQuizData(
    redisClient: Redis,
    categoryName: string,
  ): Promise<Record<string, string>[]> {
    const redisKeyPattern = `quiz:${categoryName}_*`;
    const stream = redisClient.scanStream({
      match: redisKeyPattern,
    });

    const quizDataPromises: Promise<Record<string, string>>[] = [];

    stream.on('data', function(keys: string[]) {
      for (const key of keys) {
        quizDataPromises.push(redisClient.hgetall(key));
      }
    });

    console.log(quizDataPromises);
    await once(stream, 'end');
    return await Promise.all(quizDataPromises);
  }

  /**
   * 랜덤한 퀴즈 응답을 생성하는 메서드
   *
   * @param quizDatas 퀴즈 데이터 배열
   * @returns 랜덤한 퀴즈 응답 배열
   */
  public createRandomQuizResponses(quizDatas: Record<string, string>[]): ServiceGetQuizResponse[] {
    const randomQuizResponses: ServiceGetQuizResponse[] = [];
    const randomIndices = new Set<number>();

    while (randomIndices.size < 10 && randomIndices.size < quizDatas.length) {
      const index = Math.floor(Math.random() * quizDatas.length);
      randomIndices.add(index);
    }

    for (const index of randomIndices) {
      const quizData = quizDatas[index];
      const quizChoices = this.createQuizChoices(quizData);
      const quizResponse = ServiceGetQuizResponse.create(quizData.quizTitle, quizChoices);
      randomQuizResponses.push(quizResponse);
    }

    return randomQuizResponses;
  }

  /**
   * 퀴즈 선택지 배열을 생성하는 메서드
   *
   * @param quizData 퀴즈 데이터 객체
   * @returns 퀴즈 선택지 배열
   */
  public createQuizChoices(quizData: Record<string, string>): any[] {
    const quizChoices = [];

    for (let i = 0; i < 4; i++) {
      quizChoices.push({
        content: quizData[`quizChoices.[${i}].choiceContent`],
        isAnswer: quizData[`quizChoices.[${i}].isAnswer`] === '1',
      });
    }

    quizChoices.sort(() => Math.random() - 0.5);
    return quizChoices;
  }
}
