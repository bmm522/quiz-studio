import { QuizRecord } from '../../../global/dto/QuizRecord';

export class ServiceSaveRecordRequest {
  private readonly _userKey: string;

  private readonly _quizRecordArray: QuizRecord[];

  private constructor(userKey: string, quizRecordArray: QuizRecord[]) {
    this._userKey = userKey;
    this._quizRecordArray = quizRecordArray ?? [];
  }

  static create(userKey: string, quizRecordArray: QuizRecord[]) {
    return new ServiceSaveRecordRequest(userKey, quizRecordArray);
  }

  get userKey(): string {
    return this._userKey;
  }

  get quizRecordArray(): QuizRecord[] {
    return this._quizRecordArray;
  }
}
