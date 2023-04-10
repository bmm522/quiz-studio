export class QuizRecord {
  constructor(
    public quizTitle: string,

    public quizIsAnswer: boolean,
    public quizChoiceContent: string[],
    public quizChoiceIsAnswer: boolean[],
  ) {}
}
