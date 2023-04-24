import { Records } from '../../domain/failedQuizRecords/records';

export interface FailedQuizRecordsNoSQLRepository {
  findByUserKey(userKey: string): Promise<Records[]>;
}
