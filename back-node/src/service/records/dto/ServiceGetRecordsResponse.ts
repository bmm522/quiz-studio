import {RecordDto} from "../../../global/dto/RecordDto";

export class ServiceGetRecordsResponse {
 private readonly _quizRecords: RecordDto[]

  private readonly _totalPage:number;

  private constructor(
    quizRecord:RecordDto[],
    totalPage: number,
  ) {
    this._quizRecords = quizRecord;
    this._totalPage = totalPage;
  }

  static create(
    quizRecord:RecordDto[],
    totalPage:number
  ) {
    return new ServiceGetRecordsResponse(
      quizRecord,totalPage
    );
  }


}
