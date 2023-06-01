

export class RecordDto {
  constructor(
    public quizTitle: string,

    public quizIsAnswer: boolean,

    public category: string,

    public quizChoiceContent: string[],
    public quizChoiceIsAnswer: boolean[],
  ) {}

  setCategory() {
    switch (this.category) {
      case "java":
        this.category = '자바';
        break;
      case "datastructure":
        this.category = '자료구조';
        break;
      case  "database":
        this.category='데이터베이스';
        break;
    }
  }

  // setLevel() {
  //   switch (this.level) {
  //     case Level.EASY:
  //       this.level = '쉬움';
  //       break;
  //     case Level.HARD:
  //       this.level = '어려움';
  //       break;
  //   }
  // }
}
