import { FailedQuizRecordsModel } from '../schema/FailedQuizRecordsSchema';
import { Service } from 'typedi';

import { FailedQuizRecords } from '../FailedQuizRecords';

@Service()
export class FailedQuizRecordsRepository {
  async save(dataArray: FailedQuizRecords[]): Promise<FailedQuizRecords[]> {
    const docsToInsert = dataArray.map(data => data.toSchema());
    await FailedQuizRecordsModel.insertMany(docsToInsert);
    return dataArray;
  }
}
