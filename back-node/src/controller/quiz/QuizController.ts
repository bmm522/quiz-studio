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
import { QuizCacheService } from '../../service/quiz/QuizCacheService';
import { ResponseDto } from '../common/dto/ResponseDto';
import { QuizRecordRequest } from './dto/QuizRecordRequest';
import { UserKeyRequest } from '../../jwt/dto/UserKeyRequest';
import { QuizRecordItems } from '../../service/quiz/dto/QuizRecordItems';
import { QuizControllerMapper } from './mapper/QuizControllerMapper';
import { QuizMongoDbService } from '../../service/quiz/QuizMongoDbService';

@JsonController('/quiz')
@Service()
export class QuizController {
  constructor(
    private quizCacheService: QuizCacheService,
    private quizMongoDbService: QuizMongoDbService,
  ) {}

  @HttpCode(200)
  @Get('')
  async getQuizList(@QueryParams() params: QuizParams, @Res() res: Response) {
    try {
      const result = await this.quizCacheService.getQuizList(params);
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
  async saveFailRecords(@Body() dto: QuizRecordRequest, @Req() req: UserKeyRequest) {
    try {
      const items = await this.toQuizRecordItems(dto, req);
      const result = await this.quizMongoDbService.saveFailRecords(items);
      return ResponseDto.builder()
        .withStatus(201)
        .withMessage('문제풀기 실패 기록 저장 성공');
    } catch (error) {
      throw error;
    }
  }

  // @HttpCode(200)
  // @Get('/fail-records')
  // async getFailRecords(@Req() req: UserKeyRequest) {
  //   try {
  //     const result = await this.quizMongoDbService.getFailRecords(req.userKey);
  //     return ResponseDto.builder()
  //       .withStatus(200)
  //       .withMessage('문제풀기 실패 기록 불러오기 성공')
  //       .withData(result)
  //       .build();
  //   } catch (error) {
  //     throw error;
  //   }
  // }

  private async toQuizRecordItems(
    dto: QuizRecordRequest,
    req: UserKeyRequest,
  ): Promise<QuizRecordItems> {
    return QuizControllerMapper.toQuizRecordItems(dto, req);
  }
}
