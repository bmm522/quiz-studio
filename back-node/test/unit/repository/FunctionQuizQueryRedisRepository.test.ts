import { CategoryEnum } from '../../../src/global/enum/CategoryEnum';
import Redis from 'ioredis';
import { env } from '../../../src/config/env';
import { QuizQueryRedisRepository } from '../../../src/repository/quiz/QuizQueryRedisRepository';
import { Level } from '../../../src/global/enum/Level';
import { Records } from '../../../src/domain/records/records';
import { ServiceGetQuizResponse } from '../../../src/service/quiz/dto/ServiceGetQuizResponse';
import { NotFoundEntityError } from '../../../src/error/NotFoundEntityError';

describe('QuizQueryRedisRepository Function Test', () => {
  let queryRedisRepository: QuizQueryRedisRepository;
  let redisClient: Redis;

  beforeAll(() => {
    queryRedisRepository = new QuizQueryRedisRepository();
    redisClient = queryRedisRepository.createRedisClient();
  });

  afterAll(async () => {
    await redisClient.quit();
  });

  describe('getQuizData Test', () => {
    it('성공 케이스', async () => {
      const result = await queryRedisRepository.getQuizData(
        redisClient,
        CategoryEnum.JAVA as string,
        Level.EASY as string,
      );

      expect(result.length).toBeGreaterThanOrEqual(1);

      result.forEach(quizData => {
        expect(quizData).toHaveProperty('quizTitle');
      });

      result.forEach(quizData => {
        expect(quizData.id).toContain(`${CategoryEnum.JAVA}_${Level.EASY}`);
      });

    });
  });

  describe(' createRandomQuizResponses Test', () => {
    it('성공 케이스', async () => {
      const quizDatas = await queryRedisRepository.getQuizData(
        redisClient,
        CategoryEnum.JAVA as string,
        Level.EASY as string,
      );

      const result = queryRedisRepository.createRandomQuizResponses(quizDatas);

      expect(result.length).toBeLessThanOrEqual(10);
      expect(result.length).toBeLessThanOrEqual(quizDatas.length);

      result.forEach(quizResponse => {
        expect(quizResponse).toBeInstanceOf(ServiceGetQuizResponse);
      });

      result.forEach(quizResponse => {
        const quizData = quizDatas.find(quiz => quiz.quizTitle === quizResponse.quizTitle);
        expect(quizData).toBeDefined();
      });
    });
  });

  describe('createQuizChoices Test', () => {
    it('성공 케이스', async () => {
      const quizDatas = await queryRedisRepository.getQuizData(
        redisClient,
        CategoryEnum.JAVA as string,
        Level.EASY as string,
      );

      const result = queryRedisRepository.createQuizChoices(quizDatas[0]);

      expect(result.length).toBe(4);

      result.forEach(choice => {
        expect(choice).toHaveProperty('content');
        expect(choice).toHaveProperty('isAnswer');
      });
    });
  });
});
