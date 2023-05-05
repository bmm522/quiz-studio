import { QuizQueryRedisRepository } from "../../../src/repository/quiz/QuizQueryRedisRepository";
import { CategoryEnum } from "../../../src/global/enum/CategoryEnum";
import { Level } from "../../../src/global/enum/Level";
import { ServiceGetQuizResponse } from "../../../src/service/quiz/dto/ServiceGetQuizResponse";
import { NotFoundEntityError } from "../../../src/error/NotFoundEntityError";

describe('QuizQueryRedisRepository Test', () => {
  let queryRedisRepository: QuizQueryRedisRepository;
  beforeEach(() => {
    queryRedisRepository = new QuizQueryRedisRepository();
  });
  it('정상적인 요청', async () => {
    const result = await queryRedisRepository.findByCategoryNameAndDifficulty(
      CategoryEnum.JAVA,
      Level.EASY,
    );
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
      queryRedisRepository.findByCategoryNameAndDifficulty(invalidCategoryName, invalidDifficulty),
    ).rejects.toThrow(NotFoundEntityError);
  });
});