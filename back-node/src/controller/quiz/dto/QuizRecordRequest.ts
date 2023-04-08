import {IsOptional} from "class-validator";

export class QuizRecordRequest {
    @IsOptional()
    quizRecordTitleArray: QuizRecordTitle[];

    constructor(quizRecordTitleArray: QuizRecordTitle[]) {
        this.quizRecordTitleArray = quizRecordTitleArray;
    }
}

export class QuizRecordTitle {
    quizTitle: string;
    quizChoices: string[];
    constructor(quizTitle: string, quizChoices: string[]) {
        this.quizTitle = quizTitle;
        this.quizChoices = quizChoices;
    }
}
