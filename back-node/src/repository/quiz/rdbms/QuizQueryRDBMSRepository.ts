import {CategoryEnum} from "../../../global/enum/CategoryEnum";
import {Level} from "../../../global/enum/Level";
import {Quiz} from "../../../domain/quiz/Quiz";

export interface QuizQueryRDBMSRepository {

    getQuizRandomList(category: CategoryEnum, level: Level): Promise<Quiz[] | null>;
}