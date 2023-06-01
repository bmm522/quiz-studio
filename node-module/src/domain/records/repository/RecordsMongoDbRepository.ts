import { Service } from 'typedi';

import { Records } from '../records';
import { RecordsRepository } from './RecordsRepository';
import { RecordsModel } from '../schema/recordsSchema';
import { RepositoryDeleteRecordRequest } from './dto/RepositoryDeleteRecordRequest';

@Service()
export class RecordsMongoDbRepository implements RecordsRepository {
  /**
   * 데이터를 저장하는 메서드
   *
   * @param dataArray 저장할 데이터 배열
   * @returns 저장된 데이터 배열 (Promise)
   */
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

  /**
   * 선택적으로 데이터를 삭제하는 메서드
   *
   * @param dto 삭제 요청 DTO
   * @returns 없음 (Promise)
   */
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
