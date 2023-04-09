import { Service } from 'typedi';
import { InjectRepository } from 'typeorm-typedi-extensions';
import { QuizRecordItems } from './dto/QuizRecordItems';
import { FailedQuizRecords } from '../../entity/failedQuizRecords/FailedQuizRecords';
import { QuizServiceMapper } from './mapper/QuizServiceMapper';
import { FailedQuizRecordsRepository } from '../../entity/failedQuizRecords/repository/FailedQuizRecordsRepository';

@Service()
export class CreateQuizService {
  constructor(private failedQuizRecordsRepository: FailedQuizRecordsRepository) {}

  async saveFailRecords(dto: QuizRecordItems) {

    const failedQuizRecords = await this.toFailedQuizRecords(dto);

    const savedData = await this.save(failedQuizRecords);

  }

  private async toFailedQuizRecords(dto: QuizRecordItems): Promise<FailedQuizRecords[]> {
    return QuizServiceMapper.toFailedQuizRecords(dto);
  }

  private async save(failedQuizRecords: FailedQuizRecords[]): Promise<FailedQuizRecords[]> {
    const result = await this.failedQuizRecordsRepository.save(failedQuizRecords);
    console.log("결과값 : " + result);
    return failedQuizRecords;

  }
}
