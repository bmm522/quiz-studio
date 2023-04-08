import { Service } from 'typedi';
import { InjectRepository } from 'typeorm-typedi-extensions';
import { QuizRecordItems } from './dto/QuizRecordItems';

@Service()
export class CreateQuizService {
  // constructor(@InjectRepository() private quizMongoRepository: QuizMongoRepository) {
  // }

  async saveFailRecords(dto: QuizRecordItems) {}
}
