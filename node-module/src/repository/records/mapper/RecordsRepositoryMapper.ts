import { Records } from '../../../domain/records/records';
import { RepositoryGetRecordResponse } from '../dto/RepositoryGetRecordResponse';
import { RecordDto } from '../../../global/dto/RecordDto';

export class RecordsRepositoryMapper {
  /**
   * 조회 응답을 생성하는 메서드
   *
   * @param dtos 레코드 DTO 배열
   * @param totalPage 전체 페이지 수
   * @returns 조회 응답 객체
   */
  static toGetResponse(dtos: RecordDto[], totalPage: number) {
    return RepositoryGetRecordResponse.create(dtos, totalPage);
  }
}
