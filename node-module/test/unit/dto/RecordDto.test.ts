import { RecordDto } from '../../../src/global/dto/RecordDto';
import { envJwt } from '../../../src/config/env';
import { Request } from 'express';

describe('RecordDto Test', () => {
  describe('set 함수 테스트', () => {
    let record: RecordDto;
    beforeEach(() => {
      record = new RecordDto('', true, "java", [], []);
    });
    it('Category Set Test', () => {
      record.setCategory();

      expect(record.category).toEqual('자바');
    });

    it('Level Set Test', () => {
      // expect(record.level).toEqual('쉬움');
    });
  });
});
