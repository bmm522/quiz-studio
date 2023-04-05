import {CategoryEnum} from "../../../global/enum/CategoryEnum";
import {Level} from "../../../global/enum/Level";

export class QuizListRequest {
    private category: CategoryEnum;
    private level: Level;

    constructor(category: CategoryEnum , level: Level) {
        this.category = category;
        this.level = level;
    }

    getCategory(): CategoryEnum {
        return  this.category;
    }

    getLevel(): Level {
        return this.level;
    }

}