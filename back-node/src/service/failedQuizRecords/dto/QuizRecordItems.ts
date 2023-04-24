import { IsArray, IsNotEmpty, IsString } from 'class-validator';
import { QuizRecord } from '../../../global/dto/QuizRecord';
import { QuizListItem } from '../../quiz/dto/QuizListItem';

export class QuizRecordItems {
  private readonly _userKey: string;

  private readonly _quizRecordArray: QuizRecord[];

  private constructor(userKey: string, quizRecordArray: QuizRecord[]) {
    this._userKey = userKey;
    this._quizRecordArray = quizRecordArray ?? [];
  }

  static create(userKey: string, quizRecordArray: QuizRecord[]) {
    return new QuizRecordItems(userKey, quizRecordArray);
  }

  get userKey(): string {
    return this._userKey;
  }

  get quizRecordArray(): QuizRecord[] {
    return this._quizRecordArray;
  }
}
