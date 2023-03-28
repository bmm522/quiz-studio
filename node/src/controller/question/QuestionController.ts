import {Get, JsonController, Render} from "routing-controllers";
import {Service} from "typedi";

@JsonController("/question")
@Service()
export class QuestionController {
    constructor() {

    }

    @Get("/page")
    @Render("QuestionPage.ejs")
    public async move(): Promise<void> {
        console.log("퀘스쳔페이지요청들어옴");
    }
}