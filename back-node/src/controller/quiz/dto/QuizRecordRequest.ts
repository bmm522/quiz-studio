import { IsOptional } from 'class-validator';
import { QuizRecord } from '../../../global/dto/QuizRecord';

export class QuizRecordRequest {
  @IsOptional()
  quizRecordArray: QuizRecord[];

  constructor(quizRecordArray: QuizRecord[]) {
    this.quizRecordArray = quizRecordArray ?? [];
  }
}
