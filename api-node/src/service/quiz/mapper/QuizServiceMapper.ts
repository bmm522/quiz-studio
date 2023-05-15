import { ControllerGetQuizRequest } from '../../../controller/quiz/dto/ControllerGetQuizRequest';
import { ServiceGetQuizRequest } from '../dto/ServiceGetQuizRequest';

export class QuizServiceMapper {
  static toGetRequest(params: ControllerGetQuizRequest): ServiceGetQuizRequest {
    return ServiceGetQuizRequest.create(params.category, params.level);
  }
}
