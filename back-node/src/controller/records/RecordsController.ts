import {
  Body,
  Delete,
  Get,
  HttpCode,
  JsonController,
  Post,
  QueryParams,
  Req,
} from 'routing-controllers';
import { Service } from 'typedi';
import { ControllerSaveRecordRequest } from './dto/ControllerSaveRecordRequest';
import { UserKeyRequest } from '../../jwt/dto/UserKeyRequest';
import { ResponseDto } from '../common/dto/ResponseDto';
import { RecordsService } from '../../service/records/RecordsService';
import { ServiceSaveRecordRequest } from '../../service/records/dto/ServiceSaveRecordRequest';
import { RecordsControllerMapper } from './mapper/RecordsControllerMapper';
import { ControllerDeleteRecordRequest } from './dto/ControllerDeleteRecordRequest';
import {ControllerGetRecordRequest} from "./dto/ControllerGetRecordRequest";

@JsonController('/records')
@Service()
export class RecordsController {
  constructor(private recordsService: RecordsService) {}

  @HttpCode(201)
  @Post('')
  async saveRecords(@Body() dto: ControllerSaveRecordRequest, @Req() req: UserKeyRequest) {
    try {
      const items = await this.toRecordItems(dto, req);
      const result = await this.recordsService.saveRecords(items);
      return ResponseDto.builder()
        .withStatus(201)
        .withMessage('문제풀기 기록 저장 성공');
    } catch (error) {
      throw error;
    }
  }

  @HttpCode(200)
  @Get('')
  async getRecords(@QueryParams() params: ControllerGetRecordRequest, @Req() req: UserKeyRequest) {
    try {
      console.log(params.page);
      console.log(params.unresolved);
      console.log(params.category);
      console.log(params.level);
      if (req.userKey) {
        const result = await this.recordsService.getRecords(req.userKey);
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

  @HttpCode(200)
  @Delete('')
  async deleteRecords(
    @QueryParams() params: ControllerDeleteRecordRequest,
    @Req() req: UserKeyRequest,
  ) {
    try {
      const items = await this.toDeleteRecordItems(params, req);
      const result = await this.recordsService.deleteRecords(items);
      return ResponseDto.builder()
        .withStatus(201)
        .withMessage('문제풀기 기록 삭제 성공');
    } catch (error) {
      throw error;
    }
  }

  private async toRecordItems(
    dto: ControllerSaveRecordRequest,
    req: UserKeyRequest,
  ): Promise<ServiceSaveRecordRequest> {
    return RecordsControllerMapper.toSaveRecordItems(dto, req);
  }

  private async toDeleteRecordItems(params: ControllerDeleteRecordRequest, req: UserKeyRequest) {
    return RecordsControllerMapper.toDeleteRecordItems(params, req);
  }
}
