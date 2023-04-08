import {
  QueryParams,
  Get,
  HttpCode,
  JsonController,
  QueryParam,
  Res,
  Post,
  Body,
  Req,
} from 'routing-controllers';
import { Service } from 'typedi';
import { QuizParams } from './dto/QuizParams';
import { ReadQuizService } from '../../service/quiz/ReadQuizService';
import { ResponseDto } from '../common/dto/ResponseDto';
import { QuizRecordRequest } from './dto/QuizRecordRequest';
import { CustomRequest } from '../../jwt/dto/CustomRequest';
import { CreateQuizService } from '../../service/quiz/CreateQuizService';
import { QuizRecordItems } from '../../service/quiz/dto/QuizRecordItems';
import { QuizControllerMapper } from './mapper/QuizControllerMapper';

@JsonController('/quiz')
@Service()
export class QuizController {
  constructor(
    private readQuizService: ReadQuizService,
    private createQuizService: CreateQuizService,
  ) {}

  @HttpCode(200)
  @Get('')
  async getQuizList(@QueryParams() params: QuizParams, @Res() res: Response) {
    try {
      const result = await this.readQuizService.getQuizList(params);
      return ResponseDto.builder()
        .withStatus(200)
        .withMessage('퀴즈 목록 불러오기 성공')
        .withData(result);
    } catch (error) {
      throw error;
    }
  }

  @HttpCode(201)
  @Post('/fail-records')
  async saveFailRecords(@Body() dto: QuizRecordRequest, @Req() req: CustomRequest) {
    try {
      const items = await this.toQuizRecordItems(dto, req);
      const result = await this.createQuizService.saveFailRecords(items);
      return ResponseDto.builder()
        .withStatus(201)
        .withMessage('문제풀기 실패 기록 저장 성공');
    } catch (error) {
      throw error;
    }
  }

  private async toQuizRecordItems(
    dto: QuizRecordRequest,
    req: CustomRequest,
  ): Promise<QuizRecordItems> {
    return QuizControllerMapper.toQuizRecordItems(dto, req);
  }
}
