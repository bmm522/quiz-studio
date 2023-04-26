import { RecordDto } from '../../../global/dto/RecordDto';

export class ServiceSaveRecordRequest {
  private readonly _userKey: string;

  private readonly _quizRecordArray: RecordDto[];

  private constructor(userKey: string, quizRecordArray: RecordDto[]) {
    this._userKey = userKey;
    this._quizRecordArray = quizRecordArray ?? [];
  }

  static create(userKey: string, quizRecordArray: RecordDto[]) {
    return new ServiceSaveRecordRequest(userKey, quizRecordArray);
  }

  get userKey(): string {
    return this._userKey;
  }

  get quizRecordArray(): RecordDto[] {
    return this._quizRecordArray;
  }
}
