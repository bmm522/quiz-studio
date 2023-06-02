import { ControllerGetQuizRequest } from '../../../controller/quiz/dto/ControllerGetQuizRequest';
import { ServiceGetQuizRequest } from '../dto/ServiceGetQuizRequest';

export class ServiceQuizMapper {
  static async toGetRequest(params: ControllerGetQuizRequest): Promise<ServiceGetQuizRequest> {
    return ServiceGetQuizRequest.create(params.category);
  }
}
