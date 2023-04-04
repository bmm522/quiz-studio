import jwt, { JwtPayload } from 'jsonwebtoken';
import { envJwt } from '../../config/env';
import { ExpiredTokenError } from '../../error/ExpiredTokenError';
import { UnauthorizedError } from '../../error/UnauthorizedError';

export class JwtToken {
  private jwtToken: string |  undefined;
  private refreshToken: string | undefined;

  constructor(jwtToken: string | undefined , refreshToken: string | undefined) {
    this.jwtToken = jwtToken ?? undefined;
    this.refreshToken = refreshToken ?? undefined;
  }

  getJwtToken(): string | undefined {
    return this.jwtToken;
}

getRefreshToken(): string | undefined {
    return this.refreshToken;
}


  public async checkExpiredToken() {

    if (!this.jwtToken) {
      throw new UnauthorizedError('JWT 토큰이 존재하지 않습니다.');
    }

    try {
      const decoded: JwtPayload = jwt.verify(
        this.jwtToken.split(' ')[1],
        envJwt.secretKey
      ) as JwtPayload;
  
      if (decoded.exp && decoded.exp < Date.now() / 1000) {
        throw new ExpiredTokenError('토큰의 유효시간이 만료됐습니다.');
      }
    } catch (error) {
      throw new UnauthorizedError('토큰 파싱 과정에서 에러');
    }
  }

  
  public async checkValidateJwtToken(): Promise<boolean> {
    return this.jwtToken === undefined || !this.jwtToken.startsWith(envJwt.tokenPrefix);
  }

  public async checkValidateRefreshToken(): Promise<boolean> {
    return this.refreshToken  === undefined|| !this.refreshToken.startsWith(envJwt.refreshPrefix);
  }
}