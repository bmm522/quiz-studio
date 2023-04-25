import { ControllerSaveRecordRequest } from '../dto/ControllerSaveRecordRequest';
import { ServiceSaveRecordRequest } from '../../../service/records/dto/ServiceSaveRecordRequest';
import { UserKeyRequest } from '../../../jwt/dto/UserKeyRequest';
import { UnauthorizedError } from '../../../error/UnauthorizedError';
import { ControllerDeleteRecordRequest } from '../dto/ControllerDeleteRecordRequest';
import { ServiceDeleteRecordRequest } from '../../../service/records/dto/ServiceDeleteRecordRequest';

export class RecordsControllerMapper {
  static async toSaveRecordItems(
    dto: ControllerSaveRecordRequest,
    req: UserKeyRequest,
  ): Promise<ServiceSaveRecordRequest> {
    if (!req.userKey) {
      throw new UnauthorizedError('유저키가 없음');
    }

    return ServiceSaveRecordRequest.create(req.userKey, dto.quizRecordArray);
  }

  static toDeleteRecordItems(params: ControllerDeleteRecordRequest, req: UserKeyRequest) {
    if (!req.userKey) {
      throw new UnauthorizedError('유저키가 없음');
    }
    return ServiceDeleteRecordRequest.create(params.deleteOption, req.userKey);
  }
}
