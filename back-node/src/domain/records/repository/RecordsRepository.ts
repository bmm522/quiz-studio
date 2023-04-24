import {Records} from "../records";

export interface RecordsRepository {
    save(dataArray: Records[]): Promise<Records[]>
}