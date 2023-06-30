import { UserKeyRequest } from '../dto/UserKeyRequest';
import { NextFunction, Response, Request } from 'express';
import { JwtMapper } from '../mapper/JwtMapper';
import { UnauthorizedError } from '../../error/UnauthorizedError';

/**
 * JWT 인증 필터 미들웨어
 *
 * @param req 요청 객체
 * @param res 응답 객체
 * @param next 다음 미들웨어 함수
 */
export const JwtAuthorizationFilter = async (
  req: UserKeyRequest,
  res: Response,
  next: NextFunction,
) => {
  try {
    if(req.headers.authorization === 'guest') {
      next();
      return;
    }
    const jwtToken = await JwtMapper.toJwtToken(req);
    console.log(jwtToken.getJwtToken());
    console.log(jwtToken.getRefreshToken());
    const [isJwtTokenValid, isRefreshTokenValid] = await Promise.all([
      jwtToken.checkValidateJwtToken(),
      jwtToken.checkValidateRefreshToken(),
    ]);
    if (isJwtTokenValid || isRefreshTokenValid) {
      throw new UnauthorizedError('유효하지 않은 토큰으로 요청을 보냈습니다.');
    }

    await jwtToken.checkExpiredToken();

    req.userKey = await jwtToken.getUserKeyFromJwtToken();

    next();
  } catch (err) {
    next(err);
  }
};
