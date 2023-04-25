import { Inject, Service } from 'typedi';
import { ServiceSaveRecordRequest } from './dto/ServiceSaveRecordRequest';
import { RecordsQueryMongoDbRepository } from '../../repository/records/RecordsQueryMongoDbRepository';
import { RecordsServiceMapper } from './mapper/RecordsServiceMapper';
import { RecordsMongoDbRepository } from '../../domain/records/repository/RecordsMongoDbRepository';
import { RecordsQueryRepository } from '../../repository/records/RecordsQueryRepository';
import { RecordsRepository } from '../../domain/records/repository/RecordsRepository';
import { ServiceDeleteRecordRequest } from './dto/ServiceDeleteRecordRequest';
import {ServiceRecordsResponse} from "./dto/ServiceRecordsResponse";

@Service()
export class RecordsService {
  constructor(
    @Inject(() => RecordsMongoDbRepository)
    private recordsRepository: RecordsRepository,
    @Inject(() => RecordsQueryMongoDbRepository)
    private recordsQueryRepository: RecordsQueryRepository,
  ) {}

  // 기록 저장
  async saveRecords(dto: ServiceSaveRecordRequest) : Promise<void> {
    const item = await RecordsServiceMapper.toEntities(dto);
    await this.recordsRepository.save(item);
  }

  // 기록 불러오기
  async getRecords(userKey: string): Promise<ServiceRecordsResponse[]> {
    const savedData = await this.recordsQueryRepository.findByUserKey(userKey);
    return RecordsServiceMapper.toResponse(savedData);
  }

  // 기록 삭제
  async deleteRecords(dto: ServiceDeleteRecordRequest) : Promise<void> {
    const item = await RecordsServiceMapper.toDeleteRequest(dto);
    await this.recordsRepository.deleteByOptional(item);
  }

}
