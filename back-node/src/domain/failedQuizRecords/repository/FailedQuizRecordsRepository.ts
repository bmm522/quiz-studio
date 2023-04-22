import { FailedQuizRecordsModel } from '../schema/FailedQuizRecordsSchema';
import { Service } from 'typedi';

import { FailedQuizRecords } from '../FailedQuizRecords';

@Service()
export class FailedQuizRecordsRepository {
  async save(dataArray: FailedQuizRecords[]): Promise<FailedQuizRecords[]> {
    const operations = dataArray.map(data => ({
      updateOne: {
        filter: { userKey: data.userKey, quizTitle: data.quizTitle },
        update: { $set: data.toSchema() },
        upsert: true,
      },
    }));

    await FailedQuizRecordsModel.bulkWrite(operations);
    return dataArray;
  }
}
