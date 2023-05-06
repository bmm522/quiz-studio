// import express from 'express';
// import { MongoMemoryServer } from "mongodb-memory-server";
// import { after, before } from "node:test";
// import { createExpressServer } from "routing-controllers";
// import { RecordsController } from "../../src/controller/records/RecordsController";
// import 'reflect-metadata';
// import request from 'supertest';
// import { RecordsModel } from "../../src/domain/records/schema/recordsSchema";
// import { envJwt } from "../../src/config/env";
// import * as mongoose from "mongoose";
// import * as http from "http";
// import { App } from "../../src/app";
// import { JwtToken } from "../../src/jwt/dto/JwtToken";
// import { JwtMapper } from "../../src/jwt/mapper/JwtMapper";
// const { MongoClient } = require('mongodb');
//
// describe('RecordsController', () => {
//     let app : express.Application;
//     let server: http.Server;
//     let mongoDb: MongoMemoryServer;
//     const jwtTokenValue = `${envJwt.tokenPrefix} user1`;
//     const refreshTokenValue = `${envJwt.refreshPrefix} sample_refresh_token`;
//     let jwtToken: JwtToken;
//
//     beforeAll(async () => {
//         mongoDb = await MongoMemoryServer.create();
//         const uri = mongoDb.getUri();
//
//         const serverApp = new App(uri); // App 클래스를 사용하여 서버 인스턴스를 생성합니다.
//        // await serverApp.setDatabase(uri); // 여기에서 인 메모리 데이터베이스 URI를 전달합니다.
//         app = serverApp.app; // 이제 app 인스턴스를 사용할 수 있습니다.
//
//         server = app.listen(3000);
//             const testData = {
//                 userKey: "user1",
//                 quizTitle: "Sample Quiz",
//                 quizIsAnswer: false,
//                 category: "java",
//                 level: "easy",
//                 quizChoiceContent: ["Choice 1", "Choice 2", "Choice 3", "Choice 4"],
//                 quizChoiceIsAnswer: [true, false, false, false],
//             };
//
//             await RecordsModel.create(testData);
//
//
//
//     });
//
//     afterAll(async () => {
//
//         await mongoose.disconnect();
//         await mongoDb.stop();
//         await server.close();
//
//     });
//     describe('DELETE /records', () => {
//         it('should delete a record', async () => {
//
//             jwtToken = new JwtToken(jwtTokenValue, refreshTokenValue);
//             JwtMapper.toJwtToken = jest.fn().mockResolvedValue(jwtToken);
//             jwtToken.checkExpiredToken = jest.fn().mockResolvedValue({});
//             jwtToken.getUserKeyFromJwtToken = jest.fn().mockResolvedValue('user1');
//
//             const response = await request(app)
//                 .get('/api/v1/records')
//                 .set(`authorization`, jwtTokenValue)
//                 .set(`${envJwt.refreshPrefix}`,refreshTokenValue)
//                 .query({ 'deleteOption': 'all' })
//                 .expect(401);
//
//             console.log(response.body);
//         });
//     });
// });