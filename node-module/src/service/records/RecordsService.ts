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

  /**
   * 기록을 저장하는 메서드
   *
   * @param dto 서비스 저장 요청 객체
   * @returns 저장된 기록 엔티티 배열 (Promise)
   */
  async saveRecords(dto: ServiceSaveRecordRequest): Promise<Records[]> {
    const item = await RecordsServiceMapper.toEntities(dto);
    return await this.recordsRepository.save(item);
  }

  /**
   * 기록을 조회하는 메서드
   *
   * @param dto 서비스 조회 요청 객체
   * @returns 조회 응답 객체 (Promise)
   */
  async getRecords(dto: ServiceGetRecordRequest): Promise<ServiceGetRecordsResponse> {
    const item = await RecordsServiceMapper.toGetRequest(dto);
    const response = await this.recordsQueryRepository.findByUserKey(item);

    return await RecordsServiceMapper.toGetResponse(response);
  }

  /**
   * 기록을 삭제하는 메서드
   *
   * @param dto 서비스 삭제 요청 객체
   * @returns 없음 (Promise)
   */
  async deleteRecords(dto: ServiceDeleteRecordRequest): Promise<void> {
    const item = await RecordsServiceMapper.toDeleteRequest(dto);
    await this.recordsRepository.deleteByOptional(item);
  }
}
