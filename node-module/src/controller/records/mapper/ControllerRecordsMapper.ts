import { ControllerSaveRecordRequest } from '../dto/ControllerSaveRecordRequest';
import { ServiceSaveRecordRequest } from '../../../service/records/dto/ServiceSaveRecordRequest';
import { UserKeyRequest } from '../../../jwt/dto/UserKeyRequest';
import { UnauthorizedError } from '../../../error/UnauthorizedError';
import { ControllerDeleteRecordRequest } from '../dto/ControllerDeleteRecordRequest';
import { ServiceDeleteRecordRequest } from '../../../service/records/dto/ServiceDeleteRecordRequest';
import { ControllerGetRecordRequest } from '../dto/ControllerGetRecordRequest';
import { ServiceGetRecordRequest } from '../../../service/records/dto/ServiceGetRecordRequest';
export class ControllerRecordsMapper {
  /**
   * 서비스에 전달할 저장 요청 객체로 변환하는 메서드
   *
   * @param dto 컨트롤러에서 사용하는 저장 요청 DTO
   * @param req 사용자 키 요청 객체
   * @returns 서비스에 전달할 저장 요청 객체 (Promise)
   * @throws UnauthorizedError - 사용자 키가 없을 경우 예외 발생
   */
  static async toServiceSaveRequest(
    dto: ControllerSaveRecordRequest,
    req: UserKeyRequest,
  ): Promise<ServiceSaveRecordRequest> {
    if (!req.userKey) {
      throw new UnauthorizedError('유저키가 없음');
    }

    return ServiceSaveRecordRequest.create(req.userKey, dto.quizRecordArray);
  }

  /**
   * 서비스에 전달할 삭제 요청 객체로 변환하는 메서드
   *
   * @param params 컨트롤러에서 사용하는 삭제 요청 DTO
   * @param req 사용자 키 요청 객체
   * @returns 서비스에 전달할 삭제 요청 객체 (Promise)
   * @throws UnauthorizedError - 사용자 키가 없을 경우 예외 발생
   */
  static async toServiceDeleteRequest(params: ControllerDeleteRecordRequest, req: UserKeyRequest) {
    if (!req.userKey) {
      throw new UnauthorizedError('유저키가 없음');
    }
    return ServiceDeleteRecordRequest.create(params.deleteOption, req.userKey);
  }

  /**
   * 서비스에 전달할 조회 요청 객체로 변환하는 메서드
   *
   * @param params 컨트롤러에서 사용하는 조회 요청 DTO
   * @param req 사용자 키 요청 객체
   * @returns 서비스에 전달할 조회 요청 객체 (Promise)
   * @throws UnauthorizedError - 사용자 키가 없을 경우 예외 발생
   */
  static async toServiceGetRequest(params: ControllerGetRecordRequest, req: UserKeyRequest) {
    let unresolved: boolean = false;
    if (!req.userKey) {
      throw new UnauthorizedError('유저키가 없음');
    }
    if ('true' === params.unresolved) {
      unresolved = true;
    }
    return ServiceGetRecordRequest.create(req.userKey, params.page, unresolved, params.category);
  }
}
