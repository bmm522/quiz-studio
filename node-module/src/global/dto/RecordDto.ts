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
      case 'java':
        this.category = 'Java';
        break;
      case 'datastructure':
        this.category = 'Data Structure';
        break;
      case 'database':
        this.category = 'Database';
        break;
      case 'spring':
        this.category = 'Spring';
        break;
      case 'network':
        this.category = 'Network';
        break;
      case 'interview':
        this.category = 'Interview';
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
