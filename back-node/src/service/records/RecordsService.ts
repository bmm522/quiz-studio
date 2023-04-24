import { Inject, Service } from 'typedi';
import { RecordItems } from './dto/RecordItems';
import { RecordsQueryMongoDbRepository } from '../../repository/records/RecordsQueryMongoDbRepository';
import { RecordsServiceMapper } from './mapper/RecordsServiceMapper';
import {Records} from "../../domain/records/records";
import {RecordsMongoDbRepository} from "../../domain/records/repository/RecordsMongoDbRepository";
import {RecordsQueryRepository} from "../../repository/records/RecordsQueryRepository";
import {RecordsRepository} from "../../domain/records/repository/RecordsRepository";

@Service()
export class RecordsService {
  constructor(
      @Inject(() => RecordsMongoDbRepository)
    private recordsRepository: RecordsRepository,
    @Inject(() => RecordsQueryMongoDbRepository)
    private recordsQueryRepository: RecordsQueryRepository,
  ) {}

  // 기록 저장
  async saveRecords(dto: RecordItems) {
    const failedQuizRecords = await this.toFailedQuizRecords(dto);
    await this.save(failedQuizRecords);
  }

  // 기록 불러오기
  async getRecords(userKey: string) {
    const savedData = await this.recordsQueryRepository.findByUserKey(userKey);
    return RecordsServiceMapper.toResponse(savedData);
  }

  private async toFailedQuizRecords(dto: RecordItems): Promise<Records[]> {
    return RecordsServiceMapper.toFailedQuizRecords(dto);
  }

  private async save(dataArray: Records[]): Promise<void> {
    await this.recordsRepository.save(dataArray);
  }
}
