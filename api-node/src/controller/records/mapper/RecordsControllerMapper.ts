import { ControllerSaveRecordRequest } from '../dto/ControllerSaveRecordRequest';
import { ServiceSaveRecordRequest } from '../../../service/records/dto/ServiceSaveRecordRequest';
import { UserKeyRequest } from '../../../jwt/dto/UserKeyRequest';
import { UnauthorizedError } from '../../../error/UnauthorizedError';
import { ControllerDeleteRecordRequest } from '../dto/ControllerDeleteRecordRequest';
import { ServiceDeleteRecordRequest } from '../../../service/records/dto/ServiceDeleteRecordRequest';
import { ControllerGetRecordRequest } from '../dto/ControllerGetRecordRequest';
import { ServiceGetRecordRequest } from '../../../service/records/dto/ServiceGetRecordRequest';
export class RecordsControllerMapper {
  static async toServiceSaveRequest(
    dto: ControllerSaveRecordRequest,
    req: UserKeyRequest,
  ): Promise<ServiceSaveRecordRequest> {
    if (!req.userKey) {
      throw new UnauthorizedError('유저키가 없음');
    }

    return ServiceSaveRecordRequest.create(req.userKey, dto.quizRecordArray);
  }

  static async toServiceDeleteRequest(params: ControllerDeleteRecordRequest, req: UserKeyRequest) {
    if (!req.userKey) {
      throw new UnauthorizedError('유저키가 없음');
    }
    return ServiceDeleteRecordRequest.create(params.deleteOption, req.userKey);
  }

  static async toServiceGetRequest(params: ControllerGetRecordRequest, req: UserKeyRequest) {
    let unresolved: boolean = false;
    if (!req.userKey) {
      throw new UnauthorizedError('유저키가 없음');
    }
    if ('true' === params.unresolved) {
      unresolved = true;
    }
    return ServiceGetRecordRequest.create(
      req.userKey,
      params.page,
      unresolved,
      params.category,
      // params.level,
    );
  }
}
