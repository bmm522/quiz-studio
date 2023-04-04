import { Request } from 'express';
import { JwtToken } from '../dto/JwtToken';

export class JwtMapper {
    static async toJwtToken(req: Request) {
        return new JwtToken(
            (typeof req.headers.authorization === 'string') ? req.headers.authorization?.split(' ')[1] : undefined,
            (typeof req.headers.refreshToken === 'string') ? req.headers.refreshToken?.split(' ')[1] : undefined
        )
    }
}