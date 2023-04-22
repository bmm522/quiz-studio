import { QuizRecordRequest } from '../dto/QuizRecordRequest';
import { QuizRecordItems } from '../../../service/quiz/dto/QuizRecordItems';
import { UserKeyRequest } from '../../../jwt/dto/UserKeyRequest';
import { UnauthorizedError } from '../../../error/UnauthorizedError';

export class QuizControllerMapper {
  static async toQuizRecordItems(
    dto: QuizRecordRequest,
    req: UserKeyRequest,
  ): Promise<QuizRecordItems> {
    if (!req.userKey) {
      throw new UnauthorizedError('유저키가 없음');
    }

    return new QuizRecordItems(req.userKey, dto.quizRecordArray);
  }
}
