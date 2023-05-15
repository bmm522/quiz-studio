import { Request } from 'express';
import { JwtToken } from '../dto/JwtToken';

export class JwtMapper {
  static async toJwtToken(req: Request): Promise<JwtToken> {
    return new JwtToken(
      typeof req.headers.authorization === 'string' ? req.headers.authorization : undefined,
      typeof req.headers.refreshtoken === 'string' ? req.headers.refreshtoken : undefined,
    );
  }
}
