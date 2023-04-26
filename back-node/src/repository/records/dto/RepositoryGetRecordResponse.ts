import {Records} from "../../../domain/records/records";
import {RecordDto} from "../../../global/dto/RecordDto";

export class RepositoryGetRecordResponse {
    private readonly _records: RecordDto[]

    private readonly _totalPage:number;

    private constructor(
        records:RecordDto[],
        totalPage: number,
    ) {
        this._records = records;
        this._totalPage = totalPage;
    }

    static create(
        records: RecordDto[],
        totalPage:number
    ) {
        return new RepositoryGetRecordResponse(
           records,totalPage
        );
    }
    get records(): RecordDto[] {
        return this._records;
    }

    get totalPage(): number {return this._totalPage;}
}