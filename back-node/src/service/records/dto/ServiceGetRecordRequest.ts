import {CategoryEnum} from "../../../global/enum/CategoryEnum";
import {Level} from "../../../global/enum/Level";
import {IsEnum, IsOptional} from "class-validator";

export class ServiceGetRecordRequest {
    private readonly _userKey: string;
    private readonly _page: number;
    private readonly _unresolved: boolean;
    @IsOptional()
    @IsEnum(CategoryEnum)
    private readonly _category: CategoryEnum;

    @IsOptional()
    @IsEnum(Level)
    private readonly _level: Level;

    private constructor(userKey: string, page: number, unresolved: boolean, category: CategoryEnum, level: Level)  {
        this._userKey = userKey;
        this._page = page;
        this._unresolved = unresolved;
        this._category = category;
        this._level = level;
    }

    static create(userKey: string, page: number, unresolved: boolean, category: CategoryEnum, level: Level) {
        return new ServiceGetRecordRequest(userKey, page, unresolved, category, level);
    }

    get userKey(): string {
        return this._userKey;
    }

    get page(): number {
        return this._page;
    }

    get unresolved(): boolean {
        return this._unresolved;
    }

    get category(): CategoryEnum {
        return this._category;
    }

    get level(): Level {
        return this._level;
    }
}