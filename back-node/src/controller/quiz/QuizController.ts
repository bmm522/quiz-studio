import {QueryParams, Get, HttpCode, JsonController, QueryParam, Res, Post, Body, Req} from "routing-controllers";
import { Service } from "typedi";
import {QuizParams} from "./dto/QuizParams";
import {ReadQuizService} from "../../service/quiz/ReadQuizService";
import {ResponseDto} from "../common/dto/ResponseDto";
import {QuizRecordRequest} from "./dto/QuizRecordRequest";
import {CustomRequest} from "../../jwt/dto/CustomRequest";


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
    async saveFailRecords(@Body()dto: QuizRecordRequest, @Req() req:CustomRequest) {
        console.log(req.userKey);
        console.log(dto);
        console.log(dto.quizRecordTitleArray[0]);


        // try {
        //     const result = await this.createQuizService.saveFailRecords(dto);
        // }
    }

}