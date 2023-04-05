import { Service } from "typedi";
import {QuizParams} from "../../controller/quiz/dto/QuizParams";
import {QuizServiceMapper} from "./mapper/QuizServiceMapper";
import {InjectRepository} from "typeorm-typedi-extensions";
import {QuizQueryRepository} from "../../repository/Quiz/QuizQueryRepository";
import {Quiz} from "../../entity/quiz/Quiz";

@Service()
export class ReadQuizService {

    constructor(@InjectRepository() private quizQueryRepository: QuizQueryRepository) {
    }
    async getQuizList(params: QuizParams): Promise<void> {
        const dto  = this.toDto(params);
        const result = await this.quizQueryRepository.findOneById(3);

        if (result) {
            console.log("들어옴 : " + result);
            console.log(result.quizChoices);
        }
        return undefined;
    }



    private toDto(params: QuizParams) {
        return QuizServiceMapper.toQuizListRequest(params);
    }
}