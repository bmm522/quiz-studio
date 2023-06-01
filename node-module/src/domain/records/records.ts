import { Category } from '../category/Category';

export class Records {
  userKey: string;
  quizTitle: string;

  quizIsAnswer: boolean;

  category: string;

  quizChoiceContent: string[];
  quizChoiceIsAnswer: boolean[];

  constructor(
    userKey: string,
    quizTitle: string,
    quizIsAnswer: boolean,
    category: string,
    quizChoiceContent: string[],
    quizChoiceIsAnswer: boolean[],
  ) {
    this.userKey = userKey;
    this.quizTitle = quizTitle;
    this.quizIsAnswer = quizIsAnswer;
    this.category = category;
    this.quizChoiceContent = quizChoiceContent;
    this.quizChoiceIsAnswer = quizChoiceIsAnswer;
  }

  toSchema(): {
    userKey: string;
    quizTitle: string;
    quizIsAnswer: boolean;
    category: string;
    quizChoiceContent: string[];
    quizChoiceIsAnswer: boolean[];
  } {
    return {
      userKey: this.userKey,
      quizTitle: this.quizTitle,
      quizIsAnswer: this.quizIsAnswer,
      category: this.category,
      quizChoiceContent: this.quizChoiceContent,
      quizChoiceIsAnswer: this.quizChoiceIsAnswer,
    };
  }
}
