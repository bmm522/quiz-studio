import { RecordDto } from '../../../src/global/dto/RecordDto';
import { CategoryEnum } from '../../../src/global/enum/CategoryEnum';
import { Level } from '../../../src/global/enum/Level';
import { Records } from '../../../src/domain/records/records';
import { ServiceSaveRecordRequest } from '../../../src/service/records/dto/ServiceSaveRecordRequest';
import { RecordsServiceMapper } from '../../../src/service/records/mapper/RecordsServiceMapper';
import { RepositoryGetRecordResponse } from '../../../src/repository/records/dto/RepositoryGetRecordResponse';
import { ServiceGetRecordsResponse } from '../../../src/service/records/dto/ServiceGetRecordsResponse';
import { ServiceDeleteRecordRequest } from '../../../src/service/records/dto/ServiceDeleteRecordRequest';
import { RepositoryDeleteRecordRequest } from '../../../src/domain/records/repository/dto/RepositoryDeleteRecordRequest';
import exp = require('constants');
import { ServiceGetRecordRequest } from '../../../src/service/records/dto/ServiceGetRecordRequest';
import { RepositoryGetRecordRequest } from '../../../src/repository/records/dto/RepositoryGetRecordRequest';

describe('RecordServiceMapperTest', () => {
  it('toEntities Test', async () => {
    // Given
    const userKey = 'testUserKey';
    const quizRecordArray: RecordDto[] = [
      new RecordDto(
        '퀴즈 1',
        true,
        CategoryEnum.JAVA,
        Level.EASY,
        ['선택1', '선택2', '선택3', '선택4'],
        [true, false, false, false],
      ),
      new RecordDto(
        '퀴즈 2',
        false,
        CategoryEnum.DATASTRUCTURE,
        Level.HARD,
        ['선택1', '선택2', '선택3', '선택4'],
        [false, true, false, false],
      ),
    ];

    quizRecordArray[0].setCategory();
    quizRecordArray[0].setLevel();
    quizRecordArray[1].setCategory();
    quizRecordArray[1].setLevel();

    const serviceSaveRecordRequest = ServiceSaveRecordRequest.create(userKey, quizRecordArray);

    // when
    const result = await RecordsServiceMapper.toEntities(serviceSaveRecordRequest);

    // then
    expect(result.length).toEqual(2);
    expect(result[0]).toBeInstanceOf(Records);
    expect(result[0].userKey).toEqual(userKey);
    expect(result[0].quizTitle).toEqual(quizRecordArray[0].quizTitle);
    expect(result[0].category).toEqual('자바');
    expect(result[0].level).toEqual('쉬움');

    expect(result[1]).toBeInstanceOf(Records);
    expect(result[1].userKey).toEqual(userKey);
    expect(result[1].quizTitle).toEqual(quizRecordArray[1].quizTitle);
    expect(result[1].category).toEqual('자료구조');
    expect(result[1].level).toEqual('어려움');
  });
  it('toGetResponse Test', async () => {
    const quizRecordArray: RecordDto[] = [
      new RecordDto(
        '퀴즈 1',
        true,
        CategoryEnum.JAVA,
        Level.EASY,
        ['선택1', '선택2', '선택3', '선택4'],
        [true, false, false, false],
      ),
      new RecordDto(
        '퀴즈 2',
        false,
        CategoryEnum.DATASTRUCTURE,
        Level.HARD,
        ['선택1', '선택2', '선택3', '선택4'],
        [false, true, false, false],
      ),
    ];

    const totalPage = 1;

    const repositoryGetRecordResponse = RepositoryGetRecordResponse.create(
      quizRecordArray,
      totalPage,
    );

    const result = await RecordsServiceMapper.toGetResponse(repositoryGetRecordResponse);

    expect(result).toBeInstanceOf(ServiceGetRecordsResponse);
  });

  it('toDeleteRequest Test', async () => {
    const request = ServiceDeleteRecordRequest.create('all', 'test');

    const result = await RecordsServiceMapper.toDeleteRequest(request);

    expect(result).toBeInstanceOf(RepositoryDeleteRecordRequest);
    expect(result.isAnswerOption).toEqual(undefined);
  });
  it('toGetRequest Test', async () => {
    const userKey = 'user-key';
    const page = 1;
    const unresolved = true;
    const category = CategoryEnum.JAVA;
    const level = Level.EASY;

    const serviceRequest = ServiceGetRecordRequest.create(
      userKey,
      page,
      unresolved,
      category,
      level,
    );

    const repositoryRequest = await RecordsServiceMapper.toGetRequest(serviceRequest);

    expect(repositoryRequest).toBeInstanceOf(RepositoryGetRecordRequest);
    expect(repositoryRequest.userKey).toEqual(userKey);
    expect(repositoryRequest.page).toEqual(page);
    expect(repositoryRequest.unresolved).toEqual(unresolved);
    expect(repositoryRequest.category).toEqual(category);
    expect(repositoryRequest.level).toEqual(level);
  });
});
