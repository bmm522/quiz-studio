import { envJwt } from "../../../src/config/env";
import { JwtToken } from "../../../src/jwt/dto/JwtToken";
import { UnauthorizedError } from "routing-controllers";
// @ts-ignore
import jwt from 'jsonwebtoken';
import { ExpiredTokenError } from "../../../src/error/ExpiredTokenError";

describe('JwtToken Test', () => {
    const jwtToken = `${envJwt.tokenPrefix} token`;
    const refreshToken = `${envJwt.refreshPrefix} token`;
    const userKey = 'user_key_example';
    const payload = { userKey };

    afterEach(() => {
        jest.restoreAllMocks();
    });

    it('jwtToken과 refreshToken을 반환해야 함', () => {
        const token = new JwtToken(jwtToken, refreshToken);

        expect(token.getJwtToken()).toEqual(jwtToken);
        expect(token.getRefreshToken()).toEqual(refreshToken);
    });

    it('jwtToken이 없을 경우', async () => {
        const token = new JwtToken(undefined, refreshToken);

        await expect(token.checkExpiredToken()).rejects.toThrow(
            new UnauthorizedError('JWT 토큰이 존재하지 않습니다.'),
        );
    });

    it('jwtToken이 만료된 경우', async () => {
        const token = new JwtToken(jwtToken, refreshToken);
        jest.spyOn(jwt, 'verify').mockImplementation(() => {
            throw new jwt.TokenExpiredError('jwt expired', new Date());
        });

        await expect(token.checkExpiredToken()).rejects.toThrow(
            new ExpiredTokenError('토큰의 유효시간이 만료됐습니다.'),
        );
    });

    it('jwtToken 파싱이 실패한 경우', async () => {
        const token = new JwtToken(jwtToken, refreshToken);
        jest.spyOn(jwt, 'verify').mockImplementation(() => {
            throw new Error('jwt error');
        });

        await expect(token.checkExpiredToken()).rejects.toThrow(
            new UnauthorizedError('토큰 파싱중에 에러발생'),
        );
    });

    it('jwtToken에서 userKey를 반환하는 TEST', async () => {
        const token = new JwtToken(jwtToken, refreshToken);
        jest.spyOn(jwt, 'verify').mockReturnValue(payload as any);

        const result = await token.getUserKeyFromJwtToken();

        expect(result).toEqual(userKey);
    });

    it('scheckValidateJwtToken과 checkValidateRefreshToken에 대해 boolean 값 체크', async () => {
        const token = new JwtToken(jwtToken, refreshToken);

        const isJwtTokenValid = await token.checkValidateJwtToken();
        const isRefreshTokenValid = await token.checkValidateRefreshToken();

        expect(isJwtTokenValid).toBe(false);
        expect(isRefreshTokenValid).toBe(false);
    });
});
