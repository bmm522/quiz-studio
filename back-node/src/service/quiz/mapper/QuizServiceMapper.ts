import { ControllerGetQuizRequest } from '../../../controller/quiz/dto/ControllerGetQuizRequest';
import { ServiceGetQuizRequest } from '../dto/ServiceGetQuizRequest';

export class QuizServiceMapper {
  static toGetRequest(params: ControllerGetQuizRequest): ServiceGetQuizRequest {
    return ServiceGetQuizRequest.create(params.category, params.level);
  }

  // static async toQuizResponse(data: Quiz[]): Promise<ServiceGetQuizResponse[]> {
  //   const result: ServiceGetQuizResponse[] = data.map(quiz => {
  //     const choices = quiz.quizChoices.map(choice => {
  //       return { content: choice.choiceContent, isAnswer: choice.isAnswer };
  //     });
  //     return new ServiceGetQuizResponse(quiz.quizTitle, choices);
  //   });
  //   return result;
  // }
}
