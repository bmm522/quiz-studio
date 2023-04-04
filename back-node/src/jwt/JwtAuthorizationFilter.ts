import { Request, Response, NextFunction } from 'express';
import { JwtMapper } from './mapper/JwtMapper';
import { UnauthorizedError } from '../error/UnauthorizedError';


export const JwtAuthorizationFilter = async (req: Request, res: Response, next: NextFunction) => {

    const jwtToken = await JwtMapper.toJwtToken(req);

    const [isJwtTokenValid, isRefreshTokenValid] = await Promise.all([
        jwtToken.checkValidateJwtToken(),
        jwtToken.checkValidateRefreshToken()
      ]);

      if (isJwtTokenValid || isRefreshTokenValid) {
        throw new UnauthorizedError('유효하지 않은 토큰으로 요청을 보냈습니다.');
      }

    jwtToken.checkExpiredToken();

    next();
}

