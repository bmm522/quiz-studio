import { IsEnum, IsOptional } from 'class-validator';
import { QueryParam, QueryParams } from 'routing-controllers';
import { Level } from '../../../global/enum/Level';
import { CategoryEnum } from '../../../global/enum/CategoryEnum';

export class ControllerGetQuizRequest {
  @IsEnum(CategoryEnum)
  category: CategoryEnum;

  // @IsEnum(Level)
  // level: Level;

  constructor(category: string, level: string) {
    this.category = category as CategoryEnum;
    // this.level = level as Level;
  }
}
