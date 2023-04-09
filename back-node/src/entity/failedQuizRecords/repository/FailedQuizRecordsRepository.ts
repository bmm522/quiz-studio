import { FailedQuizRecords } from '../FailedQuizRecords';
import { Service } from 'typedi';

@Service()
export class FailedQuizRecordsRepository {
  async save(data: FailedQuizRecords): Promise<FailedQuizRecords> {
    return await data.save();
  }
}
