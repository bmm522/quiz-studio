import { QuizRecordRequest } from '../dto/QuizRecordRequest';
import { RecordItems } from '../../../service/records/dto/RecordItems';
import { UserKeyRequest } from '../../../jwt/dto/UserKeyRequest';
import { UnauthorizedError } from '../../../error/UnauthorizedError';

export class QuizControllerMapper {
  static async toQuizRecordItems(
    dto: QuizRecordRequest,
    req: UserKeyRequest,
  ): Promise<RecordItems> {
    if (!req.userKey) {
      throw new UnauthorizedError('유저키가 없음');
    }

    return RecordItems.create(req.userKey, dto.quizRecordArray);
  }
}
