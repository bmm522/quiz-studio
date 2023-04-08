import {QueryParams, Get, HttpCode, JsonController, QueryParam, Res, Post, Body} from "routing-controllers";
import { Service } from "typedi";
import {QuizParams} from "./dto/QuizParams";
import {ReadQuizService} from "../../service/quiz/ReadQuizService";
import {ResponseDto} from "../common/dto/ResponseDto";
import {QuizRecordRequest} from "./dto/QuizRecordRequest";

@JsonController("/quiz")
@Service()
export class QuizController {

    constructor(private readQuizService: ReadQuizService) {}

    @HttpCode(200)
    @Get("")
    async getQuizList(@QueryParams() params: QuizParams, @Res() res: Response) {
        try {
           const result = await  this.readQuizService.getQuizList(params);
           return ResponseDto.builder().withStatus(200).withMessage("퀴즈 목록 불러오기 성공").withData(result);
        } catch (error) {
            throw  error;
        }
    }

    @HttpCode(201)
    @Post("/fail-records")
    async saveFailRecords(@Body()failQuizTitleArray: QuizRecordRequest) {
        console.log(failQuizTitleArray.quizRecordTitleArray[0]);
    }

}