import { UserKeyRequest } from "../../../src/jwt/dto/UserKeyRequest";
import {Response, NextFunction } from "express";
import { JwtToken } from "../../../src/jwt/dto/JwtToken";
import { JwtMapper } from "../../../src/jwt/mapper/JwtMapper";
import { JwtAuthorizationFilter } from "../../../src/jwt/filter/JwtAuthorizationFilter";
import { UnauthorizedError } from "routing-controllers";
import { envJwt } from "../../../src/config/env";

jest.mock('../../../src/jwt/dto/JwtToken');
jest.mock('../../../src/jwt/mapper/JwtMapper');

describe('JwtAuthorizationFilter Test', () => {
    let req: UserKeyRequest;
    let res: Response;
    let next: NextFunction;
    let jwtToken: JwtToken;
    const jwtTokenValue = `${envJwt.tokenPrefix} sample_jwt_token`;
    const refreshTokenValue = `${envJwt.refreshPrefix} sample_refresh_token`;

    beforeEach(() => {
        req = {} as UserKeyRequest;
        res = {} as Response;
        next = jest.fn();
        jwtToken = new JwtToken(jwtTokenValue, refreshTokenValue);

        (JwtMapper.toJwtToken as jest.Mock).mockResolvedValue(jwtToken);
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    it('모든 조건을 통과한 경우', async () => {

        jwtToken.checkExpiredToken = jest.fn().mockResolvedValue(true);
        jwtToken.getUserKeyFromJwtToken = jest.fn().mockResolvedValue('user_key_example');

        await JwtAuthorizationFilter(req, res, next);

        expect(next).toHaveBeenCalledWith();
    });

    it('조건을 통과하지 못한 경우', async () => {
        jwtToken.checkValidateJwtToken = jest.fn().mockResolvedValue(true);
        jwtToken.checkValidateRefreshToken = jest.fn().mockResolvedValue(true);


        await JwtAuthorizationFilter(req, res, next);

        expect(next).toHaveBeenCalledWith(
            new UnauthorizedError('유효하지 않은 토큰으로 요청을 보냈습니다.'),
        );
    });



});