

export class ServiceGetQuizRequest {
  private readonly _category: string
  // private readonly _level: Level;

  private constructor(category: string) {
    this._category = category;
    // this._level = level;
  }

  static create(category: string) {
    return new ServiceGetQuizRequest(category);
  }

  get category(): string {
    return this._category;
  }

  // get level(): Level {
  //   return this._level;
  // }
}
