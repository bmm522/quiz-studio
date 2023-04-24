import { RecordItems } from '../dto/RecordItems';

import { RecordsResponse } from '../dto/RecordsResponse';
import {Records} from "../../../domain/records/records";
import {CategoryEnum} from "../../../global/enum/CategoryEnum";
import {Level} from "../../../global/enum/Level";

export class RecordsServiceMapper {
  static async toFailedQuizRecords(dto: RecordItems): Promise<Records[]> {
    const userKey = dto.userKey;
    const quizRecordArray = dto.quizRecordArray;

    return quizRecordArray.map(quizRecord => {
      const { quizTitle, quizIsAnswer, category,level,quizChoiceContent, quizChoiceIsAnswer } = quizRecord;
      return new Records(
        userKey,
        quizTitle,
        quizIsAnswer,
        category as CategoryEnum,
        level as Level,
        quizChoiceContent,
        quizChoiceIsAnswer,
      );
    });
  }

  static async toResponse(savedData: Records[]): Promise<RecordsResponse[]> {
    let level;
    let category;
    return savedData.map(record => {
      switch (record.level) {
        case Level.EASY:
          level = '쉬움';
          break;
        case Level.HARD:
          level = '어려움';
          break;
        default:
          level = '';
      }

      switch (record.category) {
        case CategoryEnum.JAVA:
          category = '자바';
          break;
        default:
          category = '';
      }
      return RecordsResponse.create(
        record.quizTitle,
        record.quizIsAnswer,
        category,
        level,
        record.quizChoiceContent,
        record.quizChoiceIsAnswer,
      );
    });
  }
}
