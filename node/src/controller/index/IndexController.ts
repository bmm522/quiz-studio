import {Controller, Get, HttpCode, JsonController, Render, Res} from "routing-controllers";
import {Response} from "express";
import {Service} from "typedi";
@JsonController("/index")
@Service()
export class IndexController {

    constructor() {}

    @Get("")
    @Render("LoginPage.ejs")
    public async index(@Res() response: Response): Promise<void> {
        console.log("로그인페이지 요청들어옴");
    }

    @Get("/menu")
    @Render("MenuPage.ejs")
    public async menuMove(@Res() response: Response): Promise<void> {
        console.log("메뉴 요청들어옴");
    }


}