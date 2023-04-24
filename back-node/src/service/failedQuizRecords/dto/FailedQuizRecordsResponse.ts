export class FailedQuizRecordsResponse {
  private readonly _quizTitle: string;
  private readonly _quizChoiceContent: string[];
  private readonly _quizChoiceIsAnswer: boolean[];

  private constructor(
    quizTitle: string,
    quizChoiceContent: string[],
    quizChoiceIsAnswer: boolean[],
  ) {
    this._quizTitle = quizTitle;
    this._quizChoiceContent = quizChoiceContent;
    this._quizChoiceIsAnswer = quizChoiceIsAnswer;
  }

  static create(quizTitle: string, quizChoiceContent: string[], quizChoiceIsAnswer: boolean[]) {
    return new FailedQuizRecordsResponse(quizTitle, quizChoiceContent, quizChoiceIsAnswer);
  }

  get quizTitle(): string {
    return this._quizTitle;
  }

  get quizChoiceContent(): string[] {
    return this._quizChoiceContent;
  }

  get quizChoiceIsAnswer(): boolean[] {
    return this._quizChoiceIsAnswer;
  }
}
