import { FailedQuizRecordsNoSQLRepository } from './RecordsQueryRepository';
import { Service } from 'typedi';
import { Records } from '../../domain/failedQuizRecords/records';
import { FailedQuizRecordsModel } from '../../domain/failedQuizRecords/schema/recordsSchema';

@Service()
export class RecordsQueryMongoDbRepository implements FailedQuizRecordsNoSQLRepository {
  async findByUserKey(userKey: string): Promise<Records[]> {
    const records = await FailedQuizRecordsModel.find({ userKey, quizIsAnswer: false }).exec();
    return records.map(
      record =>
        new Records(
          record.userKey,
          record.quizTitle,
          record.quizIsAnswer,
          record.quizChoiceContent,
          record.quizChoiceIsAnswer,
        ),
    );
  }
}
