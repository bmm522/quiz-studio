import { Express } from "express";
import { App } from "../../src/app";
import { MongoMemoryServer } from "mongodb-memory-server";
import * as http from "http";
import { envJwt } from "../../src/config/env";
import { RecordsModel } from "../../src/domain/records/schema/recordsSchema";
import * as mongoose from "mongoose";
import request from 'supertest';
import { JwtToken } from "../../src/jwt/dto/JwtToken";
import { JwtMapper } from "../../src/jwt/mapper/JwtMapper";
describe('RecordsController', () => {

    let app: Express;
    let server: http.Server;
    let mongoDb: MongoMemoryServer
    const jwtTokenValue = `${envJwt.tokenPrefix} user1`;
    const refreshTokenValue = `${envJwt.refreshPrefix} sample_refresh_token`;

    beforeAll(async () => {
        mongoDb = await MongoMemoryServer.create();
        const uri = mongoDb.getUri();
        app = new App(uri).app;
        server = app.listen(3000);

        const testData = {
                userKey: "user1",
                quizTitle: "Sample Quiz",
                quizIsAnswer: false,
                category: "java",
                level: "easy",
                quizChoiceContent: ["Choice 1", "Choice 2", "Choice 3", "Choice 4"],
                quizChoiceIsAnswer: [true, false, false, false],
            };

            await RecordsModel.create(testData);
    });

        afterAll(async () => {

        await mongoose.disconnect();
        await server.close();

    });

    it("test", async () => {
        const jwtToken = new JwtToken(jwtTokenValue, refreshTokenValue);
            JwtMapper.toJwtToken = jest.fn().mockResolvedValue(jwtToken);
            jwtToken.checkExpiredToken = jest.fn().mockResolvedValue({});
            jwtToken.getUserKeyFromJwtToken = jest.fn().mockResolvedValue('user1');
        const response = await request(server)
                .delete('/records')
                .set(`authorization`, jwtTokenValue)
                .set(`${envJwt.refreshPrefix}`,refreshTokenValue)
                .query({ 'deleteOption': 'all' })
;

        console.log(response.body);
        console.log(response.status);
    });
});