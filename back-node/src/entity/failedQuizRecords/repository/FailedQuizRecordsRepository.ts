import { FailedQuizRecords } from '../FailedQuizRecords';
import { Service } from 'typedi';

@Service()
export class FailedQuizRecordsRepository {
  async save(data: FailedQuizRecords[]): Promise<FailedQuizRecords[]> {
    for (const failedQuizRecords of data) {
      await failedQuizRecords.save();
    }

    return data;
  }
}
