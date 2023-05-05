import { Request } from 'express';
import { JwtToken } from "../../../src/jwt/dto/JwtToken";
import { JwtMapper } from "../../../src/jwt/mapper/JwtMapper";
import { envJwt } from "../../../src/config/env";

describe('JwtMapper Test', () => {
    let req: Request;

    beforeEach(() => {
        req = {
            headers: {
                authorization: `${envJwt.tokenPrefix} jwtTokenExample`,
                refreshtoken: `${envJwt.refreshPrefix} refreshTokenExample`,
            },
        } as any as Request;
    });

    it('정상적인 상황', async () => {
        const jwtToken: JwtToken = await JwtMapper.toJwtToken(req);

        expect(jwtToken).toBeInstanceOf(JwtToken);
        expect(jwtToken.getJwtToken()).toEqual(`${envJwt.tokenPrefix} jwtTokenExample`);
        expect(jwtToken.getRefreshToken()).toEqual(`${envJwt.refreshPrefix} refreshTokenExample`);
    });

    it('만약 req 헤더가 undefined 일 경우', async () => {
        req.headers.authorization = undefined;
        req.headers.refreshtoken = undefined;

        const jwtToken: JwtToken = await JwtMapper.toJwtToken(req);

        expect(jwtToken).toBeInstanceOf(JwtToken);
        expect(jwtToken.getJwtToken()).toBeUndefined();
        expect(jwtToken.getRefreshToken()).toBeUndefined();
    });
});