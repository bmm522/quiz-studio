import { QueryParams, Get, HttpCode, JsonController, Res } from 'routing-controllers';
import { Service } from 'typedi';
import { ControllerGetQuizRequest } from './dto/ControllerGetQuizRequest';
import { QuizService } from '../../service/quiz/QuizService';
import { ResponseDto } from '../common/dto/ResponseDto';
import Response from 'express';
@JsonController('/quiz')
@Service()
export class QuizController {
  constructor(private quizService: QuizService) {}

  /**
   * 퀴즈 목록 조회 메서드
   *
   * @param params 컨트롤러에서 사용하는 퀴즈 조회 요청 DTO
   * @param res Express 응답 객체
   * @returns 퀴즈 목록 조회 결과를 담은 ResponseDto 객체
   */
  @HttpCode(200)
  @Get('')
  async getQuizList(@QueryParams() params: ControllerGetQuizRequest, @Res() res: Response) {
    try {
      const result = await this.quizService.getQuizList(params);
      return ResponseDto.builder()
        .withStatus(200)
        .withMessage('퀴즈 목록 불러오기 성공')
        .withData(result);
    } catch (error) {
      throw error;
    }
  }
}
