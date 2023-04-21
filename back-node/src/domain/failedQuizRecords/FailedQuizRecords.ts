export class FailedQuizRecords {
  private readonly userKey: string;
  private readonly quizTitle: string;

  private readonly quizIsAnswer: boolean;
  private readonly quizChoiceContent: string[];
  private readonly quizChoiceIsAnswer: boolean[];

  constructor(
    userKey: string,
    quizTitle: string,
    quizIsAnswer: boolean,
    quizChoiceContent: string[],
    quizChoiceIsAnswer: boolean[],
  ) {
    this.userKey = userKey;
    this.quizTitle = quizTitle;
    this.quizIsAnswer = quizIsAnswer;
    this.quizChoiceContent = quizChoiceContent;
    this.quizChoiceIsAnswer = quizChoiceIsAnswer;
  }

  create(): {
    userKey: string;
    quizTitle: string;
    quizIsAnswer: boolean;
    quizChoiceContent: string[];
    quizChoiceIsAnswer: boolean[];
  } {
    return {
      userKey: this.userKey,
      quizTitle: this.quizTitle,
      quizIsAnswer: this.quizIsAnswer,
      quizChoiceContent: this.quizChoiceContent,
      quizChoiceIsAnswer: this.quizChoiceIsAnswer,
    };
  }
}
