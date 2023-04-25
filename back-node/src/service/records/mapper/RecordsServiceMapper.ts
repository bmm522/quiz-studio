import { ServiceSaveRecordRequest } from '../dto/ServiceSaveRecordRequest';

import { ServiceRecordsResponse } from '../dto/ServiceRecordsResponse';
import { Records } from '../../../domain/records/records';
import { CategoryEnum } from '../../../global/enum/CategoryEnum';
import { Level } from '../../../global/enum/Level';
import { ServiceDeleteRecordRequest } from '../dto/ServiceDeleteRecordRequest';
import { RepositoryDeleteRecordRequest } from '../../../domain/records/repository/dto/RepositoryDeleteRecordRequest';

export class RecordsServiceMapper {
  static async toEntities(dto: ServiceSaveRecordRequest): Promise<Records[]> {
    const userKey = dto.userKey;
    const quizRecordArray = dto.quizRecordArray;

    return quizRecordArray.map(quizRecord => {
      const {
        quizTitle,
        quizIsAnswer,
        category,
        level,
        quizChoiceContent,
        quizChoiceIsAnswer,
      } = quizRecord;
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

  static async toResponse(savedData: Records[]): Promise<ServiceRecordsResponse[]> {
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
      return ServiceRecordsResponse.create(
        record.quizTitle,
        record.quizIsAnswer,
        category,
        level,
        record.quizChoiceContent,
        record.quizChoiceIsAnswer,
      );
    });
  }

  static toDeleteRequest(dto: ServiceDeleteRecordRequest) {
    return RepositoryDeleteRecordRequest.create(dto.userKey, dto.deleteOption);
  }
}
