import { FailedQuizRecordsModel } from '../schema/recordsSchema';
import { Service } from 'typedi';

import { Records } from '../records';

@Service()
export class RecordsRepository {
  async save(dataArray: Records[]): Promise<Records[]> {
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
