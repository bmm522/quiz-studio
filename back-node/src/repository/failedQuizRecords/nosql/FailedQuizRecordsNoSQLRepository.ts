import {FailedQuizRecords} from "../../../domain/failedQuizRecords/FailedQuizRecords";

export interface FailedQuizRecordsNoSQLRepository {


    findByUserKey(userKey: string): Promise<FailedQuizRecords[]>;
}
