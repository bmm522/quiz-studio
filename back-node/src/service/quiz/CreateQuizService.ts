import { Service } from 'typedi';
import { InjectRepository } from 'typeorm-typedi-extensions';
import { QuizRecordItems } from './dto/QuizRecordItems';
import { FailedQuizRecordsSchema } from '../../entity/failedQuizRecords/schema/FailedQuizRecordsSchema';
import { QuizServiceMapper } from './mapper/QuizServiceMapper';
import { FailedQuizRecordsRepository } from '../../entity/failedQuizRecords/repository/FailedQuizRecordsRepository';
import {FailedQuizRecords} from "../../entity/failedQuizRecords/FailedQuizRecords";

@Service()
export class CreateQuizService {
  constructor(private failedQuizRecordsRepository: FailedQuizRecordsRepository) {}

  async saveFailRecords(dto: QuizRecordItems) {

    const failedQuizRecords = await this.toFailedQuizRecords(dto);
    failedQuizRecords.pop();
      failedQuizRecords.pop();
      failedQuizRecords.pop();
      failedQuizRecords.pop();
      failedQuizRecords.pop();
      failedQuizRecords.pop();
      failedQuizRecords.pop();
      failedQuizRecords.pop();
      failedQuizRecords.pop();
    const savedData = await this.save(failedQuizRecords);

  }

  private async toFailedQuizRecords(dto: QuizRecordItems): Promise<FailedQuizRecords[]> {
    return QuizServiceMapper.toFailedQuizRecords(dto);
  }

  // private async save(failedQuizRecords: FailedQuizRecordsSchema[]): Promise<FailedQuizRecordsSchema[]> {
  //   failedQuizRecords.pop();
  //   failedQuizRecords.pop();
  //   failedQuizRecords.pop();
  //   failedQuizRecords.pop();
  //   failedQuizRecords.pop();
  //   failedQuizRecords.pop();
  //   failedQuizRecords.pop();
  //   failedQuizRecords.pop();
  //   failedQuizRecords.pop();
  //   const result = await this.failedQuizRecordsRepository.save(failedQuizRecords);
  //   console.log("결과값 : " + result);
  //   return failedQuizRecords;
  //
  // }

  private async save(dataArray: FailedQuizRecords[]): Promise<void> {await this.failedQuizRecordsRepository.save(dataArray);


  }
}
