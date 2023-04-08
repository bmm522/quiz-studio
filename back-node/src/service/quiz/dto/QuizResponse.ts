export class QuizResponse {
  private quizTitle: string;
  private quizChoice: { content: string; isAnswer: boolean }[];

  constructor(quizTitle: string, quizChoice: { content: string; isAnswer: boolean }[]) {
    this.quizTitle = quizTitle;
    this.quizChoice = quizChoice;
  }

  getQuizTitle(): string {
    return this.quizTitle;
  }
}
