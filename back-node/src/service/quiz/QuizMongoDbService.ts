import {Inject, Service} from 'typedi';
import { QuizRecordItems } from './dto/QuizRecordItems';
import { QuizServiceMapper } from './mapper/QuizServiceMapper';
import { FailedQuizRecordsRepository } from '../../domain/failedQuizRecords/repository/FailedQuizRecordsRepository';
import { FailedQuizRecords } from '../../domain/failedQuizRecords/FailedQuizRecords';
import { FailedQuizRecordsNoSQLRepository } from '../../repository/failedQuizRecords/nosql/FailedQuizRecordsNoSQLRepository';

@Service()
export class QuizMongoDbService {
  constructor(
    private failedQuizRecordsRepository: FailedQuizRecordsRepository,
    // private failedQuizRecordsNoSQLRepository: FailedQuizRecordsNoSQLRepository,
  ) {}

  async saveFailRecords(dto: QuizRecordItems) {
    const failedQuizRecords = await this.toFailedQuizRecords(dto);

    const savedData = await this.save(failedQuizRecords);
  }

  // async getFailRecords(userKey: string) {
  //   const savedData = await this.failedQuizRecordsNoSQLRepository.findByUserKey(userKey);
  // }

  private async toFailedQuizRecords(dto: QuizRecordItems): Promise<FailedQuizRecords[]> {
    return QuizServiceMapper.toFailedQuizRecords(dto);
  }

  private async save(dataArray: FailedQuizRecords[]): Promise<void> {
    await this.failedQuizRecordsRepository.save(dataArray);
  }
}
