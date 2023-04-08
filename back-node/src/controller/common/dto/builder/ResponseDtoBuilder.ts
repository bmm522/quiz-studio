import { ResponseDto } from '../ResponseDto';

export class ResponseDtoBuilder {
  private status = 0;
  private data: any;
  private message = '';

  constructor() {}

  withStatus(status: number): ResponseDtoBuilder {
    this.status = status;
    return this;
  }

  withData(data: any): ResponseDtoBuilder {
    this.data = data;
    return this;
  }

  withMessage(message: string): ResponseDtoBuilder {
    this.message = message;
    return this;
  }

  build(): ResponseDto {
    return new ResponseDto(this.status, this.message, this.data);
  }

  buildWithOutData(): ResponseDto {
    return new ResponseDto(this.status, this.message);
  }
}
