import { Service } from 'typedi';

import { Records } from '../records';
import { RecordsRepository } from './RecordsRepository';
import { RecordsModel } from '../schema/recordsSchema';
import { RepositoryDeleteRecordRequest } from './dto/RepositoryDeleteRecordRequest';

@Service()
export class RecordsMongoDbRepository implements RecordsRepository {
  async save(dataArray: Records[]): Promise<Records[]> {
    const operations = dataArray.map(data => ({
      updateOne: {
        filter: { userKey: data.userKey, quizTitle: data.quizTitle },
        update: { $set: data.toSchema() },
        upsert: true,
      },
    }));

    await RecordsModel.bulkWrite(operations);
    return dataArray;
  }

  async deleteByOptional(dto: RepositoryDeleteRecordRequest): Promise<void> {
    const filter: any = {
      userKey: dto.userKey,
    };

    if (dto.isAnswerOption) {
      filter.quizIsAnswer = dto.isAnswerOption;
    }
    try {
      await RecordsModel.deleteMany(filter);
    } catch (error) {
      throw error;
    }
  }
}
