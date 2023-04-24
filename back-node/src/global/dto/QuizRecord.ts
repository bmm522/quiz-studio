export class QuizRecord {
  constructor(
    public quizTitle: string,


    public quizIsAnswer: boolean,

    public category: string,

    public level: string,
    public quizChoiceContent: string[],
    public quizChoiceIsAnswer: boolean[],
  ) {}
}
