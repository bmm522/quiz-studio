import { Service } from 'typedi';
import { InjectRepository } from 'typeorm-typedi-extensions';
import { QuizRecordItems } from './dto/QuizRecordItems';
import { FailedQuizRecordsSchema } from '../../entity/failedQuizRecords/schema/FailedQuizRecordsSchema';
import { QuizServiceMapper } from './mapper/QuizServiceMapper';
import { FailedQuizRecordsRepository } from '../../entity/failedQuizRecords/repository/FailedQuizRecordsRepository';
import { FailedQuizRecords } from '../../entity/failedQuizRecords/FailedQuizRecords';

@Service()
export class CreateQuizService {
  constructor(private failedQuizRecordsRepository: FailedQuizRecordsRepository) {}

  async saveFailRecords(dto: QuizRecordItems) {
    const failedQuizRecords = await this.toFailedQuizRecords(dto);
    console.log(dto);

    const savedData = await this.save(failedQuizRecords);
  }

  private async toFailedQuizRecords(dto: QuizRecordItems): Promise<FailedQuizRecords[]> {
    return QuizServiceMapper.toFailedQuizRecords(dto);
  }

  private async save(dataArray: FailedQuizRecords[]): Promise<void> {
    await this.failedQuizRecordsRepository.save(dataArray);
  }
}
