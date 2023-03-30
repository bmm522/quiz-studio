import {JsonController} from "routing-controllers";

@JsonController("/index")
export class IndexController {

    constructor() {}

    @HttpCode(200)
    @Get()

}