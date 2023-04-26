import {CategoryEnum} from "../enum/CategoryEnum";
import {Level} from "../enum/Level";

export class RecordDto {
  constructor(
    public quizTitle: string,

    public quizIsAnswer: boolean,

    public category: string,

    public level: string,
    public quizChoiceContent: string[],
    public quizChoiceIsAnswer: boolean[],
  ) {}

  setCategory() {
    switch (this.category) {
      case CategoryEnum.JAVA:
        this.category = '자바';
        break;
      case CategoryEnum.DATASTRUCTURE:
        this.category = '자료구조';
        break;
      default:
        this.category = '';
    }
  }

  setLevel() {
    switch (this.level) {
      case Level.EASY:
        this.level = '쉬움';
        break;
      case Level.HARD:
        this.level = '어려움';
        break;
      default:
        this.level = '';
    }
  }
}
