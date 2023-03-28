import {Controller, Get, HttpCode, JsonController, Render, Res} from "routing-controllers";
import {Response} from "express";
import {Service} from "typedi";
@JsonController("/index")
@Service()
export class IndexController {

    constructor() {}

    @Get("")
    @Render("loginPage.ejs")
    public async index(@Res() response: Response): Promise<void> {
        console.log("요청들어옴");
    }


}