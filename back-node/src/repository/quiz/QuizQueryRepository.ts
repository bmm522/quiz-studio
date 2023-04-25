import { ServiceGetQuizResponse } from '../../service/quiz/dto/ServiceGetQuizResponse';

export interface QuizQueryRepository {
  findByCategoryNameAndDifficulty(
    categoryName: string,
    difficulty: string,
  ): Promise<ServiceGetQuizResponse[]>;
}
