import { Body, Get, HttpCode, JsonController, Post, Req } from 'routing-controllers';
import { Service } from 'typedi';
import { QuizRecordRequest } from '../quiz/dto/QuizRecordRequest';
import { UserKeyRequest } from '../../jwt/dto/UserKeyRequest';
import { ResponseDto } from '../common/dto/ResponseDto';
import { RecordsService } from '../../service/records/RecordsService';
import { RecordItems } from '../../service/records/dto/RecordItems';
import { QuizControllerMapper } from '../quiz/mapper/QuizControllerMapper';

@JsonController('/records')
@Service()
export class RecordsController {
  constructor(private recordsService: RecordsService) {}

  @HttpCode(201)
  @Post('')
  async saveFailRecords(@Body() dto: QuizRecordRequest, @Req() req: UserKeyRequest) {
    try {
      const items = await this.toQuizRecordItems(dto, req);
      const result = await this.recordsService.saveFailRecords(items);
      return ResponseDto.builder()
        .withStatus(201)
        .withMessage('문제풀기 기록 저장 성공');
    } catch (error) {
      throw error;
    }
  }

  @HttpCode(200)
  @Get('')
  async getFailRecords(@Req() req: UserKeyRequest) {
    try {
      if (req.userKey) {
        const result = await this.recordsService.getFailRecords(req.userKey);
        return ResponseDto.builder()
          .withStatus(200)
          .withMessage('문제풀기 기록 불러오기 성공')
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
  ): Promise<RecordItems> {
    return QuizControllerMapper.toQuizRecordItems(dto, req);
  }
}
