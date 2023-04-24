import { Inject, Service } from 'typedi';
import { QuizRecordItems } from './dto/QuizRecordItems';
import { FailedQuizRecordsRepository } from '../../domain/failedQuizRecords/repository/FailedQuizRecordsRepository';
import { FailedQuizRecords } from '../../domain/failedQuizRecords/FailedQuizRecords';
import { FailedQuizRecordsNoSQLRepository } from '../../repository/failedQuizRecords/nosql/FailedQuizRecordsNoSQLRepository';
import { FailedQuizRecordsQueryMongoDbRepository } from '../../repository/failedQuizRecords/nosql/FailedQuizRecordsQueryMongoDbRepository';
import { FailedQuizRecordsServiceMapper } from './mapper/FailedQuizRecordsServiceMapper';

@Service()
export class FailedQuizRecordsService {
  constructor(
    private failedQuizRecordsRepository: FailedQuizRecordsRepository,
    @Inject(() => FailedQuizRecordsQueryMongoDbRepository)
    private failedQuizRecordsNoSQLRepository: FailedQuizRecordsNoSQLRepository,
  ) {}

  // 실패기록 저장
  async saveFailRecords(dto: QuizRecordItems) {
    const failedQuizRecords = await this.toFailedQuizRecords(dto);
    await this.save(failedQuizRecords);
  }

  // 실패기록 불러오기
  async getFailRecords(userKey: string) {
    const savedData = await this.failedQuizRecordsNoSQLRepository.findByUserKey(userKey);
    return FailedQuizRecordsServiceMapper.toResponse(savedData);
  }

  private async toFailedQuizRecords(dto: QuizRecordItems): Promise<FailedQuizRecords[]> {
    return FailedQuizRecordsServiceMapper.toFailedQuizRecords(dto);
  }

  private async save(dataArray: FailedQuizRecords[]): Promise<void> {
    await this.failedQuizRecordsRepository.save(dataArray);
  }
}
