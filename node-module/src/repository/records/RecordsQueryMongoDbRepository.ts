import { RecordsQueryRepository } from './RecordsQueryRepository';
import { Service } from 'typedi';
import { Records } from '../../domain/records/records';
import { RecordsModel } from '../../domain/records/schema/recordsSchema';
import { CategoryEnum } from '../../global/enum/CategoryEnum';
import { Level } from '../../global/enum/Level';
import { RepositoryGetRecordRequest } from './dto/RepositoryGetRecordRequest';
import { RecordsRepositoryMapper } from './mapper/RecordsRepositoryMapper';
import { RepositoryGetRecordResponse } from './dto/RepositoryGetRecordResponse';
import { RecordDto } from '../../global/dto/RecordDto';

@Service()
export class RecordsQueryMongoDbRepository implements RecordsQueryRepository {
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
          record.category as CategoryEnum,
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
  category?: CategoryEnum;
}
