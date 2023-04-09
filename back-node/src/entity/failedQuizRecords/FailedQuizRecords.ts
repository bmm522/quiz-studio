export class FailedQuizRecords {
  private readonly userKey: string;
  private readonly quizTitle: string;
  private readonly quizChoiceContent: string[];
  private readonly quizChoiceIsAnswer: boolean[];

  constructor(
    userKey: string,
    quizTitle: string,
    quizChoiceContent: string[],
    quizChoiceIsAnswer: boolean[],
  ) {
    this.userKey = userKey;
    this.quizTitle = quizTitle;
    this.quizChoiceContent = quizChoiceContent;
    this.quizChoiceIsAnswer = quizChoiceIsAnswer;
  }

  getUserKey(): string {
    return this.userKey;
  }

  getQuizTitle(): string {
    return this.quizTitle;
  }

  getQuizChoiceContent(): string[] {
    return this.quizChoiceContent;
  }

  getQuizChoiceIsAnswer(): boolean[] {
    return this.quizChoiceIsAnswer;
  }
}
