export class ServiceDeleteRecordRequest {
  private readonly _deleteOption: string;

  private readonly _userKey: string;

  private constructor(deleteOption: string, userKey: string) {
    this._deleteOption = deleteOption;
    this._userKey = userKey;
  }

  static create(deleteOption: string, userKey: string) {
    return new ServiceDeleteRecordRequest(deleteOption, userKey);
  }

  get deleteOption(): string {
    return this._deleteOption;
  }

  get userKey(): string {
    return this._userKey;
  }
}
