import { CategoryEnum } from '../../../global/enum/CategoryEnum';
import { Level } from '../../../global/enum/Level';

export class ServiceGetQuizRequest {
  private readonly _category: CategoryEnum;
  // private readonly _level: Level;

  private constructor(category: CategoryEnum) {
    this._category = category;
    // this._level = level;
  }

  static create(category: CategoryEnum) {
    return new ServiceGetQuizRequest(category);
  }

  get category(): CategoryEnum {
    return this._category;
  }

  // get level(): Level {
  //   return this._level;
  // }
}
