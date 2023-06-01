import { RecordDto } from '../../../src/global/dto/RecordDto';
import { RecordsRepositoryMapper } from '../../../src/repository/records/mapper/RecordsRepositoryMapper';
import { RepositoryGetRecordResponse } from '../../../src/repository/records/dto/RepositoryGetRecordResponse';

describe('RecordsRepositoryMapperTest', () => {
  it('toGetResponse Test', async () => {
    const quizRecordArray: RecordDto[] = [
      new RecordDto(
        '퀴즈 1',
        true,
        "java",
        ['선택1', '선택2', '선택3', '선택4'],
        [true, false, false, false],
      ),
      new RecordDto(
        '퀴즈 2',
        false,
        "java",
        ['선택1', '선택2', '선택3', '선택4'],
        [false, true, false, false],
      ),
    ];

    const totalPage = 1;

    const result = await RecordsRepositoryMapper.toGetResponse(quizRecordArray, totalPage);

    expect(result).toBeInstanceOf(RepositoryGetRecordResponse);
  });
});
