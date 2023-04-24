import { FailedQuizRecordsNoSQLRepository } from './FailedQuizRecordsNoSQLRepository';
import { Service } from 'typedi';
import { FailedQuizRecords } from '../../../domain/failedQuizRecords/FailedQuizRecords';
import { FailedQuizRecordsModel } from '../../../domain/failedQuizRecords/schema/FailedQuizRecordsSchema';

@Service()
export class FailedQuizRecordsQueryMongoDbRepository implements FailedQuizRecordsNoSQLRepository {
  async findByUserKey(userKey: string): Promise<FailedQuizRecords[]> {
    const records = await FailedQuizRecordsModel.find({ userKey, quizIsAnswer: false }).exec();
    return records.map(
      record =>
        new FailedQuizRecords(
          record.userKey,
          record.quizTitle,
          record.quizIsAnswer,
          record.quizChoiceContent,
          record.quizChoiceIsAnswer,
        ),
    );
  }
}
