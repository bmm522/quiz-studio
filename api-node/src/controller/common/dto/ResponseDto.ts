import { ResponseDtoBuilder } from './builder/ResponseDtoBuilder';

export class ResponseDto {
  private readonly status: number;
  private readonly message: string;
  private readonly data: any | null;

  constructor(status: number, message: string, data?: any) {
    this.status = status;
    this.message = message;
    this.data = data ?? null;
  }

  static builder(): ResponseDtoBuilder {
    return new ResponseDtoBuilder();
  }

  getStatus(): number {
    return this.status;
  }

  getData(): any {
    return this.data;
  }

  getMessage(): string {
    return this.message;
  }
}
