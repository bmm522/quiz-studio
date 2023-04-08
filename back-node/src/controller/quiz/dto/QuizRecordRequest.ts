import {IsOptional} from "class-validator";

export class QuizRecordRequest {
    @IsOptional()
    quizRecordTitleArray: QuizRecord[];

    constructor(quizRecordTitleArray: QuizRecord[]) {
        this.quizRecordTitleArray = quizRecordTitleArray ?? [];
    }

}
export class QuizRecord {
    constructor(
        public quizTitle: string,
        public quizChoices: QuizChoices[]
    ) {}
}

export class QuizChoices {
    constructor(
        public quizChoiceContent: string,
        public quizChoiceIsAnswer: boolean
    ) {}
}