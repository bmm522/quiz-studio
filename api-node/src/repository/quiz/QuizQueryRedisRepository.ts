import { Service } from 'typedi';
import Redis from 'ioredis';
import { env } from '../../config/env';
import { ServiceGetQuizResponse } from '../../service/quiz/dto/ServiceGetQuizResponse';
import { once } from 'events';
import { QuizQueryRepository } from './QuizQueryRepository';
import { NotFoundEntityError } from '../../error/NotFoundEntityError';
@Service()
export class QuizQueryRedisRepository implements QuizQueryRepository {
  async findByCategoryNameAndDifficulty(
    categoryName: string,
    // difficulty: string,
  ): Promise<ServiceGetQuizResponse[]> {
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

    await once(stream, 'end');
    return await Promise.all(quizDataPromises);
  }

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
