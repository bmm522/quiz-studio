export class QuizResponse {
  private readonly _quizTitle: string;
  private readonly _quizChoice: { content: string; isAnswer: boolean }[];

  private constructor(quizTitle: string, quizChoice: { content: string; isAnswer: boolean }[]) {
    this._quizTitle = quizTitle;
    this._quizChoice = quizChoice;
  }

  static create(quizTitle: string, quizChoice: { content: string; isAnswer: boolean }[]) {
    return new QuizResponse(quizTitle, quizChoice);
  }

  get quizTitle(): string {
    return this._quizTitle;
  }
}
