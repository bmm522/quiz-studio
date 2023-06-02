import { ServiceSaveRecordRequest } from '../dto/ServiceSaveRecordRequest';

import { ServiceGetRecordsResponse } from '../dto/ServiceGetRecordsResponse';
import { Records } from '../../../domain/records/records';
import { ServiceDeleteRecordRequest } from '../dto/ServiceDeleteRecordRequest';
import { RepositoryDeleteRecordRequest } from '../../../domain/records/repository/dto/RepositoryDeleteRecordRequest';
import { ServiceGetRecordRequest } from '../dto/ServiceGetRecordRequest';
import { RepositoryGetRecordRequest } from '../../../repository/records/dto/RepositoryGetRecordRequest';
import { RepositoryGetRecordResponse } from '../../../repository/records/dto/RepositoryGetRecordResponse';
import { RecordDto } from '../../../global/dto/RecordDto';

export class ServiceRecordsMapper {
  /**
   * DTO를 엔티티 배열로 변환하는 메서드
   *
   * @param dto 서비스 저장 요청 DTO
   * @returns 레코드 엔티티 배열 (Promise)
   */
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
        category,
        quizChoiceContent,
        quizChoiceIsAnswer,
      );
    });
  }
  /**
   * 조회 응답을 생성하는 메서드
   *
   * @param response 레포지토리 조회 응답 객체
   * @returns 서비스 조회 응답 객체 (Promise)
   */
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

  /**
   * 삭제 요청 객체로 변환하는 메서드
   *
   * @param dto 서비스 삭제 요청 DTO
   * @returns 레포지토리 삭제 요청 객체
   */
  static async toDeleteRequest(
    dto: ServiceDeleteRecordRequest,
  ): Promise<RepositoryDeleteRecordRequest> {
    return RepositoryDeleteRecordRequest.create(dto.userKey, dto.deleteOption);
  }

  /**
   * 조회 요청 객체로 변환하는 메서드
   *
   * @param dto 서비스 조회 요청 DTO
   * @returns 레포지토리 조회 요청 객체
   */
  static async toGetRequest(dto: ServiceGetRecordRequest): Promise<RepositoryGetRecordRequest> {
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
