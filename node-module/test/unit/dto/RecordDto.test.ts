import { RecordDto } from '../../../src/global/dto/RecordDto';
import { envJwt } from '../../../src/config/env';
import { Request } from 'express';

describe('RecordDto Test', () => {
  describe('set 함수 테스트', () => {
    let record: RecordDto;

    it('Java Category Set Test', () => {
      record = new RecordDto('', true, "java", [], []);
      record.setCategory();

      expect(record.category).toEqual('Java');
    });

    it('DataStructure Category Set Test', () => {
      record = new RecordDto('', true, "datastructure", [], []);
      record.setCategory();

      expect(record.category).toEqual('Data Structure');
    });

    it('Database Category Set Test', () => {
      record = new RecordDto('', true, "database", [], []);
      record.setCategory();

      expect(record.category).toEqual('Database');
    });

    it('Spring Category Set Test', () => {
      record = new RecordDto('', true, "spring", [], []);
      record.setCategory();

      expect(record.category).toEqual('Spring');
    });

    it('Network Category Set Test', () => {
      record = new RecordDto('', true, "network", [], []);
      record.setCategory();

      expect(record.category).toEqual('Network');
    });

    it('Interview Category Set Test', () => {
      record = new RecordDto('', true, "interview", [], []);
      record.setCategory();

      expect(record.category).toEqual('Interview');
    });

    it('Level Set Test', () => {
      // expect(record.level).toEqual('쉬움');
    });
  });
});
