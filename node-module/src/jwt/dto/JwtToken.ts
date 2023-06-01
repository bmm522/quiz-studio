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

  /**
   * 토큰의 만료 여부를 확인하고 유효한 경우 토큰 페이로드를 반환하는 메서드
   *
   * @returns 토큰 페이로드 (Promise)
   * @throws UnauthorizedError - JWT 토큰이 존재하지 않거나 파싱 중 에러가 발생한 경우 예외 발생
   * @throws ExpiredTokenError - 토큰의 유효시간이 만료된 경우 예외 발생
   */
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

  /**
   * JWT 토큰에서 사용자 키를 추출하는 메서드
   *
   * @returns 사용자 키 (Promise)
   * @throws UnauthorizedError - JWT 토큰이 존재하지 않거나 파싱 중 에러가 발생한 경우 예외 발생
   */
  public async getUserKeyFromJwtToken(): Promise<string> {
    const decodeJwt = await this.checkExpiredToken();
    return decodeJwt.userKey;
  }

  /**
   * JWT 토큰의 유효성을 확인하는 메서드
   *
   * @returns JWT 토큰의 유효성 여부 (Promise)
   */
  public async checkValidateJwtToken(): Promise<boolean> {
    return this.jwtToken === undefined || !this.jwtToken.startsWith(envJwt.tokenPrefix);
  }

  /**
   * 리프레시 토큰의 유효성을 확인하는 메서드
   *
   * @returns 리프레시 토큰의 유효성 여부 (Promise)
   */
  public async checkValidateRefreshToken(): Promise<boolean> {
    return this.refreshToken === undefined || !this.refreshToken.startsWith(envJwt.refreshPrefix);
  }
}
