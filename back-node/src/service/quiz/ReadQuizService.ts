import { Service } from "typedi";
import {QuizParams} from "../../controller/quiz/dto/QuizParams";
import {QuizServiceMapper} from "./mapper/QuizServiceMapper";
import {InjectRepository} from "typeorm-typedi-extensions";
import {QuizQueryRepository} from "../../repository/Quiz/QuizQueryRepository";
import {Quiz} from "../../entity/quiz/Quiz";
import {QuizListRequest} from "./dto/QuizListRequest";
import {NotFoundEntityError} from "../../error/NotFoundEntityError";
import {QuizResponse} from "./dto/QuizResponse";

@Service()
export class ReadQuizService {

    constructor(@InjectRepository() private quizQueryRepository: QuizQueryRepository) {
    }
    async getQuizList(params: QuizParams): Promise<QuizResponse[]> {
        const item  = await this.toRequest(params);
        const quizRandomList = await this.getQuizRandomList(item);
        return  await this.toResponse( quizRandomList);
    }

    private async getQuizRandomList(item: QuizListRequest) : Promise<Quiz[]>{
        const quizList =  await this.quizQueryRepository.getQuizRandomList(item.getCategory(), item.getLevel());

        if(!quizList) {
            throw new NotFoundEntityError('해당 퀴즈 랜덤 리스트를 찾을 수 없습니다.');
        }

        return quizList;
    }



    private async toRequest(params: QuizParams) : Promise<QuizListRequest> {
        return QuizServiceMapper.toQuizListRequest(params);
    }

    private async toResponse(quizRandomList : Quiz[]): Promise<QuizResponse[]> {
        return QuizServiceMapper.toQuizResponse(quizRandomList);
    }
}