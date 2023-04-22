import {QuizResponse} from "../../../service/quiz/dto/QuizResponse";

export interface QuizQueryCacheRepository {
    findByCategoryNameAndDifficulty(
        categoryName: string,
        difficulty: string,
    ): Promise<QuizResponse[]>
}