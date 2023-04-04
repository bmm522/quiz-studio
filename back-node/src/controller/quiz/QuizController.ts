import {QueryParams, Get, HttpCode, JsonController, QueryParam} from "routing-controllers";
import { Service } from "typedi";
import {LevelRequest} from "./dto/LevelRequest";

@JsonController("/quiz")
@Service()
export class QuizController {

    constructor() {}

    @HttpCode(200)
    @Get("/test")
    async test(@QueryParams()query: LevelRequest) {
        console.log(query);
    }

}