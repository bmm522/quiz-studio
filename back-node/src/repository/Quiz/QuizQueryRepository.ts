import {createQueryBuilder, EntityRepository} from "typeorm";
import {Quiz} from "../../entity/quiz/Quiz";

@EntityRepository(Quiz)
export class QuizQueryRepository {
    async findOneById(id: number): Promise<Quiz | null>{
        return createQueryBuilder()
            .select("quiz")
            .from(Quiz, "quiz")
            .leftJoinAndSelect("quiz.category", "category")
            .leftJoinAndSelect("quiz.quizChoices", "quizChoice")
            .where("quiz.quizId = :id", { id })
            .getOne();
    }
}