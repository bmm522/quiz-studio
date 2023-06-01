import { IsEnum, IsOptional } from 'class-validator';

export class ControllerGetRecordRequest {
  page: number;
  unresolved: string;

  category: string;

  // @IsOptional()
  // @IsEnum(Level)
  // level: Level;

  constructor(page: number, unresolved: string, category: string) {
    this.page = page;
    this.unresolved = unresolved;
    this.category = category;
    // this.level = level as Level;
  }
}
