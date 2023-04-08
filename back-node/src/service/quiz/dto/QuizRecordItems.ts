import { QuizRecord } from '../../../global/dto/QuizRecord';

export class QuizRecordItems {
  private quizRecordArray: QuizRecord[];
  private userKey: string;

  constructor(quizRecordArray: QuizRecord[], userKey: string) {
    this.quizRecordArray = quizRecordArray;
    this.userKey = userKey;
  }
  getQuizRecordArray(): QuizRecord[] {
    return this.quizRecordArray;
  }

  getUserKey(): string {
    return this.userKey;
  }
}
