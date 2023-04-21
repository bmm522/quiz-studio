import { Service } from 'typedi';
import { QuizRecordItems } from './dto/QuizRecordItems';
import { QuizServiceMapper } from './mapper/QuizServiceMapper';
import { FailedQuizRecordsRepository } from '../../domain/failedQuizRecords/repository/FailedQuizRecordsRepository';
import { FailedQuizRecords } from '../../domain/failedQuizRecords/FailedQuizRecords';

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

  private async save(dataArray: FailedQuizRecords[]): Promise<void> {
    await this.failedQuizRecordsRepository.save(dataArray);
  }
}
