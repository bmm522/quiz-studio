export class RepositoryDeleteRecordRequest {
  private readonly _userKey: string;
  private readonly _isAnswerOption: boolean | undefined;

  private constructor(userKey: string, deleteOption: string) {
    this._userKey = userKey;
    switch (deleteOption) {
      case 'all':
        this._isAnswerOption = undefined;
        break;
      case 'resolved':
        this._isAnswerOption = true;
        break;
      default:
        this._isAnswerOption = undefined;
    }
  }

  static create(userKey: string, deleteOption: string): RepositoryDeleteRecordRequest {
    return new RepositoryDeleteRecordRequest(userKey, deleteOption);
  }

  get userKey(): string {
    return this._userKey;
  }

  get isAnswerOption(): boolean | undefined {
    return this._isAnswerOption;
  }
}
