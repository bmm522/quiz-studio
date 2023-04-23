import { IsArray, IsNotEmpty, IsString } from 'class-validator';
import { QuizRecord } from '../../../global/dto/QuizRecord';

export class QuizRecordItems {
  private readonly userKey: string;

  private readonly quizRecordArray: QuizRecord[];

  constructor(userKey: string, quizRecordArray: QuizRecord[]) {
    this.userKey = userKey;
    this.quizRecordArray = quizRecordArray ?? [];
  }

  getUserKey(): string {
    return this.userKey;
  }

  getQuizRecordArray(): QuizRecord[] {
    return this.quizRecordArray;
  }
}
