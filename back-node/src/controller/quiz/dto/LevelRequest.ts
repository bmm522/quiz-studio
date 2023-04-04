import {IsOptional} from "class-validator";
import {QueryParam, QueryParams} from "routing-controllers";

export class LevelRequest {
    category: string ;

    level: string;

    constructor(category: string, level: string) {
        this.category = category;
        this.level = level;
    }
}