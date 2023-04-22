import { FailedQuizRecordsModel} from '../schema/FailedQuizRecordsSchema';
import { Service } from 'typedi';

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
    console.log("durl");
    await Promise.all(promises);
    console.log("222");
    return dataArray;
  }
}
