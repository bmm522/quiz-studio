import { QuizParams } from '../../../controller/quiz/dto/QuizParams';
import { QuizListItem } from '../dto/QuizListItem';
import { Quiz } from '../../../entity/quiz/Quiz';
import { QuizResponse } from '../dto/QuizResponse';
import { QuizRecordItems } from '../dto/QuizRecordItems';
import { FailedQuizRecords } from '../../../entity/failedQuizRecords/FailedQuizRecords';
import {QuizRecord} from "../../../global/dto/QuizRecord";

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
        const promises = [];

        for (const { quizTitle, quizChoiceContent, quizChoiceIsAnswer } of quizRecordArray) {
            const failedQuizRecord = FailedQuizRecords.create({
                userKey,
                quizTitle,
                quizChoiceContent,
                quizChoiceIsAnswer,
            });
            promises.push(failedQuizRecord);
        }

        return Promise.all(promises);
    }
}
