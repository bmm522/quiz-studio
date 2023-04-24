import { QuizResponse } from '../../service/quiz/dto/QuizResponse';

export interface QuizQueryRepository {
  findByCategoryNameAndDifficulty(
    categoryName: string,
    difficulty: string,
  ): Promise<QuizResponse[]>;
}
