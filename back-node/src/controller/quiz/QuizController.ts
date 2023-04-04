import { Get, JsonController } from "routing-controllers";
import { Service } from "typedi";

@JsonController("/quiz")
@Service()
export class QuizController {

    constructor() {}

    @Get("/java")
    public async getJave

}