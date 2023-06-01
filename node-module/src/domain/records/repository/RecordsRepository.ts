import { Records } from '../records';
import { RepositoryDeleteRecordRequest } from './dto/RepositoryDeleteRecordRequest';

export interface RecordsRepository {
  save(dataArray: Records[]): Promise<Records[]>;

  deleteByOptional(dto: RepositoryDeleteRecordRequest): void;
}
