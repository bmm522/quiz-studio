import { ServiceSaveRecordRequest } from '../dto/ServiceSaveRecordRequest';

import { ServiceGetRecordsResponse } from '../dto/ServiceGetRecordsResponse';
import { Records } from '../../../domain/records/records';
import { CategoryEnum } from '../../../global/enum/CategoryEnum';
import { Level } from '../../../global/enum/Level';
import { ServiceDeleteRecordRequest } from '../dto/ServiceDeleteRecordRequest';
import { RepositoryDeleteRecordRequest } from '../../../domain/records/repository/dto/RepositoryDeleteRecordRequest';
import { ServiceGetRecordRequest } from '../dto/ServiceGetRecordRequest';
import { RepositoryGetRecordRequest } from '../../../repository/records/dto/RepositoryGetRecordRequest';
import { RepositoryGetRecordResponse } from '../../../repository/records/dto/RepositoryGetRecordResponse';
import { RecordDto } from '../../../global/dto/RecordDto';

export class RecordsServiceMapper {
  static async toEntities(dto: ServiceSaveRecordRequest): Promise<Records[]> {
    const userKey = dto.userKey;
    const quizRecordArray = dto.quizRecordArray;

    return quizRecordArray.map(quizRecord => {
      const {
        quizTitle,
        quizIsAnswer,
        category,
        quizChoiceContent,
        quizChoiceIsAnswer,
      } = quizRecord;
      return new Records(
        userKey,
        quizTitle,
        quizIsAnswer,
        category as CategoryEnum,
        quizChoiceContent,
        quizChoiceIsAnswer,
      );
    });
  }

  static async toGetResponse(
    response: RepositoryGetRecordResponse,
  ): Promise<ServiceGetRecordsResponse> {

    const records = await Promise.all(
      response.records.map(async record => {
        await record.setCategory();
        return record;
      }),
    );

    return ServiceGetRecordsResponse.create(records, response.totalPage);
  }

  static toDeleteRequest(dto: ServiceDeleteRecordRequest) {
    return RepositoryDeleteRecordRequest.create(dto.userKey, dto.deleteOption);
  }

  static async toGetRequest(dto: ServiceGetRecordRequest) {
    if (dto.category) {
    }
    return RepositoryGetRecordRequest.create(
      dto.userKey,
      dto.page,
      dto.unresolved,
      dto.category,
      // dto.level,
    );
  }
}
