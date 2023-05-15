import { RepositoryDeleteRecordRequest } from '../../../src/domain/records/repository/dto/RepositoryDeleteRecordRequest';
import exp = require('constants');

describe('RepositoryDeleteRecordRequest Test', () => {
  let userKey = 'test';
  let all = 'all';
  let resolved = 'resolved';
  let unregisteredVariables = 'unregisteredVariables';
  it('deleteOption all 일 경우', async () => {
    const result = RepositoryDeleteRecordRequest.create(userKey, all);

    expect(result.isAnswerOption).toBe(undefined);
  });
  it('deleteOption resolved 일 경우', async () => {
    const result = RepositoryDeleteRecordRequest.create(userKey, resolved);

    expect(result.isAnswerOption).toBe(true);
  });
  it('deleteOption 등록되지 않은 변수가 들어올경우', async () => {
    const result = RepositoryDeleteRecordRequest.create(userKey, unregisteredVariables);

    expect(result.isAnswerOption).toBe(undefined);
  });
});
