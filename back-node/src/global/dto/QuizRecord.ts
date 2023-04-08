export class QuizRecord {
  constructor(public quizTitle: string, public quizChoices: QuizChoices[]) {}

  getQuizTitle(): string {
    return this.quizTitle;
  }

  getQuizChoices(): QuizChoices[] {
    return this.quizChoices;
  }
}

export class QuizChoices {
  constructor(public quizChoiceContent: string, public quizChoiceIsAnswer: boolean) {}

  getQuizChoiceContent(): string {
    return this.quizChoiceContent;
  }

  getQuizChoiceIsAnswer(): boolean {
    return this.quizChoiceIsAnswer;
  }
}
