import jwt, { JwtPayload } from 'jsonwebtoken';
import { envJwt } from '../../config/env';
import { ExpiredTokenError } from '../../error/ExpiredTokenError';
import { UnauthorizedError } from '../../error/UnauthorizedError';

export class JwtToken {
  private readonly jwtToken: string | undefined;
  private readonly refreshToken: string | undefined;

  constructor(jwtToken: string | undefined, refreshToken: string | undefined) {
    this.jwtToken = jwtToken ?? undefined;
    this.refreshToken = refreshToken ?? undefined;
  }

  getJwtToken(): string | undefined {
    return this.jwtToken;
  }

  getRefreshToken(): string | undefined {
    return this.refreshToken;
  }

  public async checkExpiredToken(): Promise<JwtPayload> {
    if (!this.jwtToken) {
      throw new UnauthorizedError('JWT 토큰이 존재하지 않습니다.');
    }
    try {
      return jwt.verify(this.jwtToken.split(' ')[1], envJwt.secretKey) as JwtPayload;
    } catch (err) {
      if (err instanceof jwt.TokenExpiredError) {
        throw new ExpiredTokenError('토큰의 유효시간이 만료됐습니다.');
      } else {
        throw new UnauthorizedError('토큰 파싱중에 에러발생');
      }
    }
  }

  public async getUserKeyFromJwtToken(): Promise<string> {
    const decodeJwt = await this.checkExpiredToken();
    return decodeJwt.userKey;
  }

  public async checkValidateJwtToken(): Promise<boolean> {
    return this.jwtToken === undefined || !this.jwtToken.startsWith(envJwt.tokenPrefix);
  }

  public async checkValidateRefreshToken(): Promise<boolean> {
    return this.refreshToken === undefined || !this.refreshToken.startsWith(envJwt.refreshPrefix);
  }
}
