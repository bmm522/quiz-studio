import {QuizParams} from "../../../controller/quiz/dto/QuizParams";
import {QuizListRequest} from "../dto/QuizListRequest";


export class QuizServiceMapper {
    static toQuizListRequest(params: QuizParams): QuizListRequest {
        return new QuizListRequest(
            params.category,
            params.level
        )
    }
}