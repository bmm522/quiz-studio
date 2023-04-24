import { QuizRecordItems } from '../dto/QuizRecordItems';
import { FailedQuizRecords } from '../../../domain/failedQuizRecords/FailedQuizRecords';
import { FailedQuizRecordsResponse } from '../dto/FailedQuizRecordsResponse';

export class FailedQuizRecordsServiceMapper {
  static async toFailedQuizRecords(dto: QuizRecordItems): Promise<FailedQuizRecords[]> {
    const userKey = dto.getUserKey();
    const quizRecordArray = dto.getQuizRecordArray();

    return quizRecordArray.map(quizRecord => {
      const { quizTitle, quizIsAnswer, quizChoiceContent, quizChoiceIsAnswer } = quizRecord;
      return new FailedQuizRecords(
        userKey,
        quizTitle,
        quizIsAnswer,
        quizChoiceContent,
        quizChoiceIsAnswer,
      );
    });
  }

  static async toResponse(savedData: FailedQuizRecords[]): Promise<FailedQuizRecordsResponse[]> {
    return savedData.map(quiz => {
      return FailedQuizRecordsResponse.create(
        quiz.quizTitle,
        quiz.quizChoiceContent,
        quiz.quizChoiceIsAnswer,
      );
    });
  }
}
