import {QueryParams, Get, HttpCode, JsonController, QueryParam} from "routing-controllers";
import { Service } from "typedi";
import {QuizParams} from "./dto/QuizParams";
import {ReadQuizService} from "../../service/quiz/ReadQuizService";

@JsonController("/quiz")
@Service()
export class QuizController {

    constructor(private readQuizServer: ReadQuizService) {}

    @HttpCode(200)
    @Get("")
    async getQuizList(@QueryParams() params: QuizParams) {
        try {
           await  this.readQuizServer.getQuizList(params);
        } catch (error) {
            throw  error;
        }
    }

}