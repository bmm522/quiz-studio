import { QuizParams } from '../../../controller/quiz/dto/QuizParams';
import { QuizListItem } from '../dto/QuizListItem';
import { Quiz } from '../../../entity/quiz/Quiz';
import { QuizResponse } from '../dto/QuizResponse';
import { QuizRecordItems } from '../dto/QuizRecordItems';
import { FailedQuizRecordsSchema } from '../../../entity/failedQuizRecords/schema/FailedQuizRecordsSchema';
import {QuizRecord} from "../../../global/dto/QuizRecord";
import {FailedQuizRecords} from "../../../entity/failedQuizRecords/FailedQuizRecords";

export class QuizServiceMapper {
  static toQuizListRequest(params: QuizParams): QuizListItem {
    return new QuizListItem(params.category, params.level);
  }

  static async toQuizResponse(data: Quiz[]): Promise<QuizResponse[]> {
    const result: QuizResponse[] = data.map(quiz => {
      const choices = quiz.quizChoices.map(choice => {
        return { content: choice.choiceContent, isAnswer: choice.isAnswer };
      });
      return new QuizResponse(quiz.quizTitle, choices);
    });
    return result;
  }

    static async toFailedQuizRecords(dto: QuizRecordItems): Promise<FailedQuizRecords[]> {
        const userKey = dto.getUserKey();
        const quizRecordArray = dto.getQuizRecordArray();

        const failedQuizRecords: FailedQuizRecords[] = [];

        for (let quizRecord of quizRecordArray) {
            const quizTitle = quizRecord.quizTitle;
            const quizChoiceContent = quizRecord.quizChoiceContent;
            const quizChoiceIsAnswer = quizRecord.quizChoiceIsAnswer;

            const failedQuizRecord = new FailedQuizRecords(userKey, quizTitle, quizChoiceContent, quizChoiceIsAnswer);
            failedQuizRecords.push(failedQuizRecord);
        }

        return failedQuizRecords;
    }
}
