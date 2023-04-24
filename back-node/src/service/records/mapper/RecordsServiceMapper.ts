import { RecordItems } from '../dto/RecordItems';
import { Records } from '../../../domain/failedQuizRecords/records';
import { RecordsResponse } from '../dto/RecordsResponse';

export class RecordsServiceMapper {
  static async toFailedQuizRecords(dto: RecordItems): Promise<Records[]> {
    const userKey = dto.userKey;
    const quizRecordArray = dto.quizRecordArray;

    return quizRecordArray.map(quizRecord => {
      const { quizTitle, quizIsAnswer, quizChoiceContent, quizChoiceIsAnswer } = quizRecord;
      return new Records(
        userKey,
        quizTitle,
        quizIsAnswer,
        quizChoiceContent,
        quizChoiceIsAnswer,
      );
    });
  }

  static async toResponse(savedData: Records[]): Promise<RecordsResponse[]> {
    return savedData.map(quiz => {
      return RecordsResponse.create(
        quiz.quizTitle,
        quiz.quizChoiceContent,
        quiz.quizChoiceIsAnswer,
      );
    });
  }
}
