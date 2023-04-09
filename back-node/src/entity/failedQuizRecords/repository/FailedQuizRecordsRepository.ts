import { FailedQuizRecordsModel, FailedQuizRecordsSchema } from '../schema/FailedQuizRecordsSchema';
import { Service } from 'typedi';
import { QuizRecordItems } from '../../../service/quiz/dto/QuizRecordItems';
import { FailedQuizRecords } from '../FailedQuizRecords';

@Service()
export class FailedQuizRecordsRepository {
  async save(dataArray: FailedQuizRecords[]): Promise<FailedQuizRecords[]> {
    const promises = dataArray.map(data => {
      const { userKey,quizTitle, quizIsAnswer,quizChoiceContent, quizChoiceIsAnswer } = data.create();
      return FailedQuizRecordsModel.create({
        userKey,
        quizTitle,
        quizIsAnswer,
        quizChoiceContent,
        quizChoiceIsAnswer,
      });
    });
    await Promise.all(promises);
    return dataArray;
  }
}
