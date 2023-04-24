export class Records {
  userKey: string;
  quizTitle: string;

  quizIsAnswer: boolean;
  quizChoiceContent: string[];
  quizChoiceIsAnswer: boolean[];

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

  toSchema(): {
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
