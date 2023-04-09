export class QuizRecord {
  constructor(
    public quizTitle: string,
    public quizChoiceContent: string[],
    public quizChoiceIsAnswer: boolean[],
  ) {}

  getQuizTitle(): string {
    return this.quizTitle;
  }

  getQuizChoicesContent(): string[] {
    return this.quizChoiceContent;
  }

  getQuizChoicesIsAnswer(): boolean[] {
    return this.quizChoiceIsAnswer;
  }
}
