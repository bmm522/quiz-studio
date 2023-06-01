import { CategoryEnum } from '../../../global/enum/CategoryEnum';
import { IsEnum, IsOptional } from 'class-validator';
import { Level } from '../../../global/enum/Level';

export class ControllerGetRecordRequest {
  page: number;
  unresolved: string;

  @IsOptional()
  @IsEnum(CategoryEnum)
  category: CategoryEnum;

  // @IsOptional()
  // @IsEnum(Level)
  // level: Level;

  constructor(page: number, unresolved: string, category: string) {
    this.page = page;
    this.unresolved = unresolved;
    this.category = category as CategoryEnum;
    // this.level = level as Level;
  }
}
