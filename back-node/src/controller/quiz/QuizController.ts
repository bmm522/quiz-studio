import {Get, JsonController, QueryParams} from "routing-controllers";
import { Service } from "typedi";

@JsonController("/quiz")
@Service()
export class QuizController {

    constructor() {}

    @Get("/test")
    public async test(){
        console.log("테스트성공");
    }

}