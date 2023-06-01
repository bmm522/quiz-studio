import { CategoryEnum } from '../../../src/global/enum/CategoryEnum';
import Redis from 'ioredis';
import { env } from '../../../src/config/env';
import { QuizQueryRedisRepository } from '../../../src/repository/quiz/QuizQueryRedisRepository';
import { Level } from '../../../src/global/enum/Level';
import { Records } from '../../../src/domain/records/records';
import { ServiceGetQuizResponse } from '../../../src/service/quiz/dto/ServiceGetQuizResponse';
import { NotFoundEntityError } from '../../../src/error/NotFoundEntityError';

describe('QuizQueryRedisRepository Test', () => {
  let queryRedisRepository: QuizQueryRedisRepository;
  beforeEach(() => {
    queryRedisRepository = new QuizQueryRedisRepository();
  });
  if (env.nodeEnv.testEnv === 'development') {
    it('정상적인 요청', async () => {
      const result = await queryRedisRepository.findByCategoryNameAndDifficulty(CategoryEnum.JAVA);

      expect(result.length).toBeGreaterThan(0);

      result.forEach(quizResponse => {
        expect(quizResponse).toBeInstanceOf(ServiceGetQuizResponse);
      });
    });
    it('실패 케이스: 주어진 카테고리 이름과 난이도에 해당하는 퀴즈가 없을 경우', async () => {
      const invalidCategoryName = 'INVALID_CATEGORY';
      const invalidDifficulty = 'INVALID_DIFFICULTY';

      // 3. 주어진 카테고리 이름과 난이도에 해당하는 퀴즈가 없을 경우 에러를 발생시키는지 확인
      await expect(
        queryRedisRepository.findByCategoryNameAndDifficulty(invalidCategoryName),
      ).rejects.toThrow(NotFoundEntityError);
    });

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
          );

          expect(result.length).toBeGreaterThanOrEqual(1);

          result.forEach(quizData => {
            expect(quizData).toHaveProperty('quizTitle');
          });

          result.forEach(quizData => {
            expect(quizData.id).toContain(`${CategoryEnum.JAVA}_`);
          });
        });
      });

      describe(' createRandomQuizResponses Test', () => {
        it('성공 케이스', async () => {
          const quizDatas = await queryRedisRepository.getQuizData(
            redisClient,
            CategoryEnum.JAVA as string,
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
  } else {
    it.skip('정상적인 요청', () => {});
    it.skip('실패 케이스: 주어진 카테고리 이름과 난이도에 해당하는 퀴즈가 없을 경우', () => {});

    it.skip('getQuizData Test 성공케이스', () => {});
    it.skip('createRandomQuizResponses Test 성공케이스', () => {});
    it.skip('createQuizChoices Test 케이스', () => {});
  }
});
