import { Service } from 'typedi';
import Redis from 'ioredis';
import { env } from '../../../config/env';
import { QuizResponse } from '../../../service/quiz/dto/QuizResponse';
import { once } from 'events';
import {QuizQueryCacheRepository} from "./QuizQueryCacheRepository";
@Service()
export class QuizQueryRedisRepository implements QuizQueryCacheRepository{
  key: string = 'test';
  data: string = 'ggg';

  async findByCategoryNameAndDifficulty(
    categoryName: string,
    difficulty: string,
  ): Promise<QuizResponse[]> {
    const redisClient = new Redis({
      host: env.redis.host as string,
      port: parseInt(env.redis.port as string),
    });

    const redisKeyPattern = `quiz:${categoryName}_${difficulty}*`;
    const randomQuizResponses: QuizResponse[] = [];

    try {
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

      const quizDatas = await Promise.all(quizDataPromises);

      const randomIndices = new Set<number>();
      while (randomIndices.size < 10 && randomIndices.size < quizDatas.length) {
        const index = Math.floor(Math.random() * quizDatas.length);
        randomIndices.add(index);
      }

      for (const index of randomIndices) {
        const quizData = quizDatas[index];

        const quizChoices = [];
        for (let i = 0; i < 4; i++) {
          quizChoices.push({
            content: quizData[`quizChoices.[${i}].choiceContent`],
            isAnswer: quizData[`quizChoices.[${i}].isAnswer`] === '1',
          });
        }

        quizChoices.sort(() => Math.random() - 0.5);

        const quizResponse = new QuizResponse(quizData.quizTitle, quizChoices);
        randomQuizResponses.push(quizResponse);
      }

      return randomQuizResponses;
    } catch (err) {
      console.error(err);
      return [];
    } finally {
      await redisClient.quit();
    }
  }
}
