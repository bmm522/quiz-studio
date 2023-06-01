import { RecordsQueryRepository } from './RecordsQueryRepository';
import { Service } from 'typedi';
import { Records } from '../../domain/records/records';
import { RecordsModel } from '../../domain/records/schema/recordsSchema';
import { RepositoryGetRecordRequest } from './dto/RepositoryGetRecordRequest';
import { RecordsRepositoryMapper } from './mapper/RecordsRepositoryMapper';
import { RepositoryGetRecordResponse } from './dto/RepositoryGetRecordResponse';
import { RecordDto } from '../../global/dto/RecordDto';

@Service()
export class RecordsQueryMongoDbRepository implements RecordsQueryRepository {
  /**
   * 사용자 키에 해당하는 레코드를 조회하는 메서드
   *
   * @param dto 조회 요청 객체
   * @returns 조회 응답 객체 (Promise)
   */
  async findByUserKey(dto: RepositoryGetRecordRequest): Promise<RepositoryGetRecordResponse> {
    let userKey = dto.userKey;
    let unresolved = dto.unresolved;
    let category = dto.category;
    // let level = dto.level;
    let page = dto.page;
    let pageSize = 10; // 페이지당 레코드 수
    // 쿼리 객체 생성
    let query: QueryObject = {
      userKey,
    };
    // 필요한 조건 추가
    if (unresolved) {
      query['quizIsAnswer'] = false;
    }

    if (category) {
      query['category'] = category;
    }

    // if (level) {
    //   query['level'] = level;
    // }

    if (!page) {
      page = 1;
    }
    const totalRecords = await RecordsModel.countDocuments(query).exec();
    const totalPage = Math.ceil(totalRecords / pageSize);
    const records = await RecordsModel.find(query)
      .sort({ createdAt: -1 })
      .skip((page - 1) * pageSize)
      .limit(pageSize)
      .exec();

    const recordPromises = records.map(
      record =>
        new RecordDto(
          record.quizTitle,
          record.quizIsAnswer,
          record.category,
          record.quizChoiceContent,
          record.quizChoiceIsAnswer,
        ),
    );

    const recordObjects = await Promise.all(recordPromises);
    return RecordsRepositoryMapper.toGetResponse(recordObjects, totalPage);
  }
}
interface QueryObject {
  userKey: string;
  quizIsAnswer?: boolean;
  category?: string;
}
