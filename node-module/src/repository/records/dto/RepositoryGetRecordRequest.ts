import { IsEnum, IsOptional } from 'class-validator';

export class RepositoryGetRecordRequest {
  private readonly _userKey: string;
  private readonly _page: number;
  private readonly _unresolved: boolean;
  @IsOptional()
  private readonly _category: string;

  // @IsOptional()
  // @IsEnum(Level)
  // private readonly _level: Level;

  private constructor(
    userKey: string,
    page: number,
    unresolved: boolean,
    category: string,
    // level: Level,
  ) {
    this._userKey = userKey;
    this._page = page;
    this._unresolved = unresolved;
    this._category = category;
    // this._level = level;
  }

  static create(
    userKey: string,
    page: number,
    unresolved: boolean,
    category: string,
    // level: Level,
  ) {
    return new RepositoryGetRecordRequest(userKey, page, unresolved, category);
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

  get category(): string | undefined {
    return this._category;
  }

  // get level(): Level | undefined {
  //   return this._level;
  // }
}
