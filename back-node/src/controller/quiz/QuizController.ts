import { QueryParams, Get, HttpCode, JsonController, Res } from 'routing-controllers';
import { Service } from 'typedi';
import { ControllerGetQuizRequest } from './dto/ControllerGetQuizRequest';
import { QuizService } from '../../service/quiz/QuizService';
import { ResponseDto } from '../common/dto/ResponseDto';

@JsonController('/quiz')
@Service()
export class QuizController {
  constructor(private quizService: QuizService) {}

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
