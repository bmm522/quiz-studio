import { Body, Get, HttpCode, JsonController, Post, Req } from 'routing-controllers';
import { Service } from 'typedi';
import { QuizRecordRequest } from '../quiz/dto/QuizRecordRequest';
import { UserKeyRequest } from '../../jwt/dto/UserKeyRequest';
import { ResponseDto } from '../common/dto/ResponseDto';
import { FailedQuizRecordsService } from '../../service/failedQuizRecords/FailedQuizRecordsService';
import { QuizRecordItems } from '../../service/failedQuizRecords/dto/QuizRecordItems';
import { QuizControllerMapper } from '../quiz/mapper/QuizControllerMapper';

@JsonController('/fail-records')
@Service()
export class FailedQuizRecordsController {
  constructor(private failedQuizRecordsService: FailedQuizRecordsService) {}

  @HttpCode(201)
  @Post('')
  async saveFailRecords(@Body() dto: QuizRecordRequest, @Req() req: UserKeyRequest) {
    try {
      const items = await this.toQuizRecordItems(dto, req);
      const result = await this.failedQuizRecordsService.saveFailRecords(items);
      return ResponseDto.builder()
        .withStatus(201)
        .withMessage('문제풀기 실패 기록 저장 성공');
    } catch (error) {
      throw error;
    }
  }

  @HttpCode(200)
  @Get('')
  async getFailRecords(@Req() req: UserKeyRequest) {
    try {
      if (req.userKey) {
        const result = await this.failedQuizRecordsService.getFailRecords(req.userKey);
        return ResponseDto.builder()
          .withStatus(200)
          .withMessage('문제풀기 실패 기록 불러오기 성공')
          .withData(result)
          .build();
      }
    } catch (error) {
      throw error;
    }
  }

  private async toQuizRecordItems(
    dto: QuizRecordRequest,
    req: UserKeyRequest,
  ): Promise<QuizRecordItems> {
    return QuizControllerMapper.toQuizRecordItems(dto, req);
  }
}
