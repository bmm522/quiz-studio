import { Records } from '../../domain/records/records';
import {RepositoryGetRecordRequest} from "./dto/RepositoryGetRecordRequest";
import {RepositoryGetRecordResponse} from "./dto/RepositoryGetRecordResponse";

export interface RecordsQueryRepository {
  findByUserKey(dto: RepositoryGetRecordRequest): Promise<RepositoryGetRecordResponse>;
}
