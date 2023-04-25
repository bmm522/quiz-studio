export class ServiceRecordsResponse {
  private readonly _quizTitle: string;
  private readonly _quizIsAnswer: boolean;

  private readonly _category: string;

  private readonly _level: string;
  private readonly _quizChoiceContent: string[];
  private readonly _quizChoiceIsAnswer: boolean[];

  private constructor(
    quizTitle: string,
    quizIsAnswer: boolean,
    category: string,
    level: string,
    quizChoiceContent: string[],
    quizChoiceIsAnswer: boolean[],
  ) {
    this._quizTitle = quizTitle;
    this._quizIsAnswer = quizIsAnswer;
    this._category = category;
    this._level = level;
    this._quizChoiceContent = quizChoiceContent;
    this._quizChoiceIsAnswer = quizChoiceIsAnswer;
  }

  static create(
    quizTitle: string,
    quizIsAnswer: boolean,
    category: string,
    level: string,
    quizChoiceContent: string[],
    quizChoiceIsAnswer: boolean[],
  ) {
    return new ServiceRecordsResponse(
      quizTitle,
      quizIsAnswer,
      category,
      level,
      quizChoiceContent,
      quizChoiceIsAnswer,
    );
  }
}
