import {QueryParams, Get, HttpCode, JsonController, QueryParam, Res} from "routing-controllers";
import { Service } from "typedi";
import {QuizParams} from "./dto/QuizParams";
import {ReadQuizService} from "../../service/quiz/ReadQuizService";
import {ResponseDto} from "../common/dto/ResponseDto";

@JsonController("/quiz")
@Service()
export class QuizController {

    constructor(private readQuizServer: ReadQuizService) {}

    @HttpCode(200)
    @Get("")
    async getQuizList(@QueryParams() params: QuizParams, @Res() res: Response) {
        try {
           const result = await  this.readQuizServer.getQuizList(params);
           return ResponseDto.builder().withStatus(200).withMessage("퀴즈 목록 불러오기 성공").withData(result);
        } catch (error) {
            throw  error;
        }
    }

}