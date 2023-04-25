import { Records } from '../../domain/records/records';

export interface RecordsQueryRepository {
  findByUserKey(userKey: string): Promise<Records[]>;
}
