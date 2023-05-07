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
import { ControllerGetRecordRequest } from './dto/ControllerGetRecordRequest';
import { Request } from 'express';
@JsonController('/records')
@Service()
export class RecordsController {
  constructor(private recordsService: RecordsService) {}

  @HttpCode(201)
  @Post('')
  async saveRecords(@Body() dto: ControllerSaveRecordRequest, @Req() req: UserKeyRequest) {
    try {
      const items = await RecordsControllerMapper.toServiceSaveRequest(dto, req);

      const result = await this.recordsService.saveRecords(items);
      return ResponseDto.builder()
        .withStatus(201)
        .withMessage('문제풀기 기록 저장 성공')
          .withData(result);
    } catch (error) {
      throw error;
    }
  }

  @HttpCode(200)
  @Get('')
  async getRecords(@QueryParams() params: ControllerGetRecordRequest, @Req() req: UserKeyRequest) {
    try {
        const dto = await RecordsControllerMapper.toServiceGetRequest(params, req);
        const result = await this.recordsService.getRecords(dto);
        return ResponseDto.builder()
          .withStatus(200)
          .withMessage('문제풀기 기록 불러오기 성공')
          .withData(result)
          .build();

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

      const dto = await RecordsControllerMapper.toServiceDeleteRequest(params, req);
      const result = await this.recordsService.deleteRecords(dto);
      return ResponseDto.builder()
        .withStatus(200)
        .withMessage(`문제풀기 기록 삭제 성공 option:${dto.deleteOption}`);
    } catch (error) {
      throw error;
    }
  }

  // private async toRecordItems(
  //   dto: ControllerSaveRecordRequest,
  //   req: UserKeyRequest,
  // ): Promise<ServiceSaveRecordRequest> {
  //   return RecordsControllerMapper.toSaveServiceDto(dto, req);
  // }
  //
  // private async toDeleteRecordItems(params: ControllerDeleteRecordRequest, req: UserKeyRequest) {
  //   return RecordsControllerMapper.toDeleteServoceDto(params, req);
  // }
}
