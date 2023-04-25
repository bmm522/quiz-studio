import { IsOptional } from 'class-validator';
import { QuizRecord } from '../../../global/dto/QuizRecord';

export class ControllerSaveRecordRequest {
  @IsOptional()
  quizRecordArray: QuizRecord[];

  constructor(quizRecordArray: QuizRecord[]) {
    this.quizRecordArray = quizRecordArray ?? [];
  }
}
