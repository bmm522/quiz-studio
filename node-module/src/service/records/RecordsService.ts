import { Inject, Service } from 'typedi';
import { ServiceSaveRecordRequest } from './dto/ServiceSaveRecordRequest';
import { RecordsQueryMongoDbRepository } from '../../repository/records/RecordsQueryMongoDbRepository';
import { RecordsServiceMapper } from './mapper/RecordsServiceMapper';
import { RecordsMongoDbRepository } from '../../domain/records/repository/RecordsMongoDbRepository';
import { RecordsQueryRepository } from '../../repository/records/RecordsQueryRepository';
import { RecordsRepository } from '../../domain/records/repository/RecordsRepository';
import { ServiceDeleteRecordRequest } from './dto/ServiceDeleteRecordRequest';
import { ServiceGetRecordsResponse } from './dto/ServiceGetRecordsResponse';
import { ServiceGetRecordRequest } from './dto/ServiceGetRecordRequest';
import { Records } from '../../domain/records/records';

@Service()
export class RecordsService {
  constructor(
    @Inject(() => RecordsMongoDbRepository)
    private recordsRepository: RecordsRepository,
    @Inject(() => RecordsQueryMongoDbRepository)
    private recordsQueryRepository: RecordsQueryRepository,
  ) {}

  // 기록 저장
  async saveRecords(dto: ServiceSaveRecordRequest): Promise<Records[]> {
    const item = await RecordsServiceMapper.toEntities(dto);
    return await this.recordsRepository.save(item);
  }

  // 기록 불러오기
  async getRecords(dto: ServiceGetRecordRequest): Promise<ServiceGetRecordsResponse> {
    const item = await RecordsServiceMapper.toGetRequest(dto);
    const response = await this.recordsQueryRepository.findByUserKey(item);

    return await RecordsServiceMapper.toGetResponse(response);
  }

  // 기록 삭제
  async deleteRecords(dto: ServiceDeleteRecordRequest): Promise<void> {
    const item = await RecordsServiceMapper.toDeleteRequest(dto);
    await this.recordsRepository.deleteByOptional(item);
  }
}
