import { RecordsQueryRepository } from './RecordsQueryRepository';
import { Service } from 'typedi';
import { Records } from '../../domain/records/records';
import { RecordsModel } from '../../domain/records/schema/recordsSchema';
import { CategoryEnum } from '../../global/enum/CategoryEnum';
import { Level } from '../../global/enum/Level';

@Service()
export class RecordsQueryMongoDbRepository implements RecordsQueryRepository {
  async findByUserKey(userKey: string): Promise<Records[]> {
    const records = await RecordsModel.find({ userKey }).exec();
    return records.map(
      record =>
        new Records(
          record.userKey,
          record.quizTitle,
          record.quizIsAnswer,
          record.category as CategoryEnum,
          record.level as Level,
          record.quizChoiceContent,
          record.quizChoiceIsAnswer,
        ),
    );
  }
}
