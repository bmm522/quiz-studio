import { Inject, Service } from 'typedi';
import { RecordItems } from './dto/RecordItems';
import { FailedQuizRecordsNoSQLRepository } from '../../repository/records/RecordsQueryRepository';
import { RecordsQueryMongoDbRepository } from '../../repository/records/RecordsQueryMongoDbRepository';
import { RecordsServiceMapper } from './mapper/RecordsServiceMapper';
import {Records} from "../../domain/records/records";
import {RecordsRepository} from "../../domain/records/repository/RecordsRepository";

@Service()
export class RecordsService {
  constructor(
    private failedQuizRecordsRepository: RecordsRepository,
    @Inject(() => RecordsQueryMongoDbRepository)
    private failedQuizRecordsNoSQLRepository: FailedQuizRecordsNoSQLRepository,
  ) {}

  // 실패기록 저장
  async saveFailRecords(dto: RecordItems) {
    const failedQuizRecords = await this.toFailedQuizRecords(dto);
    await this.save(failedQuizRecords);
  }

  // 실패기록 불러오기
  async getFailRecords(userKey: string) {
    const savedData = await this.failedQuizRecordsNoSQLRepository.findByUserKey(userKey);
    return RecordsServiceMapper.toResponse(savedData);
  }

  private async toFailedQuizRecords(dto: RecordItems): Promise<Records[]> {
    return RecordsServiceMapper.toFailedQuizRecords(dto);
  }

  private async save(dataArray: Records[]): Promise<void> {
    await this.failedQuizRecordsRepository.save(dataArray);
  }
}
