export class QuizRecord {
  constructor(public quizTitle: string, public quizChoices: QuizChoices[]) {}
}

export class QuizChoices {
  constructor(public quizChoiceContent: string, public quizChoiceIsAnswer: boolean) {}
}
