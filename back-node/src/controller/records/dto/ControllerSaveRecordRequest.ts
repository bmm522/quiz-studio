import { IsOptional } from 'class-validator';
import { RecordDto } from '../../../global/dto/RecordDto';

export class ControllerSaveRecordRequest {
  @IsOptional()
  quizRecordArray: RecordDto[];

  constructor(quizRecordArray: RecordDto[]) {
    this.quizRecordArray = quizRecordArray ?? [];
  }
}
