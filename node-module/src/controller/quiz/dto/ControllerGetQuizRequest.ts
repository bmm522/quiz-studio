import { IsEnum, IsOptional } from 'class-validator';
import { QueryParam, QueryParams } from 'routing-controllers';


export class ControllerGetQuizRequest {

  category: string;

  // @IsEnum(Level)
  // level: Level;

  constructor(category: string) {
    this.category = category ;
    // this.level = level as Level;
  }
}
