import { Records } from '../../../domain/records/records';
import { RepositoryGetRecordResponse } from '../dto/RepositoryGetRecordResponse';
import { RecordDto } from '../../../global/dto/RecordDto';

export class RepositoryMapper {
  static toGetResponse(dtos: RecordDto[], totalPage: number) {
    return RepositoryGetRecordResponse.create(dtos, totalPage);
  }
}
