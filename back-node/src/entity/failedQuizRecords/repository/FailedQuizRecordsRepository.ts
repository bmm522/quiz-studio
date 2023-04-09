import { FailedQuizRecordsModel, FailedQuizRecordsSchema } from '../schema/FailedQuizRecordsSchema';
import { Service } from 'typedi';
import { QuizRecordItems } from '../../../service/quiz/dto/QuizRecordItems';
import { FailedQuizRecords } from '../FailedQuizRecords';

@Service()
export class FailedQuizRecordsRepository {
  async save(dataArray: FailedQuizRecords[]): Promise<FailedQuizRecordsSchema[]> {
    const promises = [];
    for (let data of dataArray) {
      let userKey = data.getUserKey();
      let quizTitle = data.getQuizTitle();
      let quizChoiceContent = data.getQuizChoiceContent();
      let quizChoiceIsAnswer = data.getQuizChoiceIsAnswer();
      const failedQuizRecord = FailedQuizRecordsModel.create({
        userKey,
        quizTitle,
        quizChoiceContent,
        quizChoiceIsAnswer,
      });
      promises.push(failedQuizRecord);
    }
    return Promise.all(promises);
  }
}
