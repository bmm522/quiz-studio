import express , {  NextFunction } from "express";
import "reflect-metadata"
import { App } from "../../src/app";
import { MongoMemoryServer } from "mongodb-memory-server";
import * as http from "http";
import { envJwt } from "../../src/config/env";
import { RecordsModel } from "../../src/domain/records/schema/recordsSchema";
import * as mongoose from "mongoose";
import { JwtToken } from "../../src/jwt/dto/JwtToken";
import { JwtMapper } from "../../src/jwt/mapper/JwtMapper";
import { RecordsController } from "../../src/controller/records/RecordsController";
import request from 'supertest';
import { JwtAuthorizationFilter } from "../../src/jwt/filter/JwtAuthorizationFilter";
import { UserKeyRequest } from "../../src/jwt/dto/UserKeyRequest";

jest.mock('../../src/jwt/filter/JwtAuthorizationFilter');

describe('RecordsController', () => {
    let app: express.Application;
    let server: http.Server;
    let mongoDb: MongoMemoryServer

    beforeAll(async () => {
        mongoDb = await MongoMemoryServer.create();
        const uri = mongoDb.getUri();
        app = new App(uri).app;
        (JwtAuthorizationFilter as jest.Mock).mockImplementation(
            (req: UserKeyRequest, res: Response, next: NextFunction) => {
                req.userKey = 'user1';
                next();
            },
        );
        // server = app.listen(0);
        const testData = {
                userKey: "user1",
                quizTitle: "Sample Quiz11",
                quizIsAnswer: false,
                category: "java",
                level: "easy",
                quizChoiceContent: ["Choice 1", "Choice 2", "Choice 3", "Choice 4"],
                quizChoiceIsAnswer: [true, false, false, false],
            };

            await RecordsModel.create(testData);
        const testData2 = {
            userKey: "user1",
            quizTitle: "Sample Quiz22",
            quizIsAnswer: false,
            category: "java",
            level: "easy",
            quizChoiceContent: ["Choice 1", "Choice 2", "Choice 3", "Choice 4"],
            quizChoiceIsAnswer: [true, false, false, false],
        };

        const testData3 = {
            userKey: "user1",
            quizTitle: "Sample Quiz33",
            quizIsAnswer: false,
            category: "java",
            level: "easy",
            quizChoiceContent: ["Choice 1", "Choice 2", "Choice 3", "Choice 4"],
            quizChoiceIsAnswer: [true, false, false, false],
        };


        await RecordsModel.create(testData);
        await RecordsModel.create(testData2);
        await RecordsModel.create(testData3);
    });

        afterAll(async () => {
            // await server.close();
            await mongoDb.stop();

    });

    it("save test", async () => {
        const testData2 = {
            quizTitle: "Sample Quiz2",
            category: "java",
            level: "easy",
            quizChoiceContent: ["Choice 1", "Choice 2", "Choice 3", "Choice 4"],
            quizChoiceIsAnswer: [true, false, false, false],
        };

        const testData3 = {
            quizTitle: "Sample Quiz3",
            category: "gggg",
            level: "easy",
            quizChoiceContent: ["Choice 1", "Choice 2", "Choice 3", "Choice 4"],
            quizChoiceIsAnswer: [true, false, false, false],
        };

        const recordDtos = {
            quizRecordArray: [testData2, testData3],
        };

        const response = await request(app)
            .post('/api/v1/records')
            .send( recordDtos )
        ;
        // console.log(response.error);
        expect(response.status).toBe(201);
        expect(response.body.message).toBe('문제풀기 기록 저장 성공');
        expect(response.body.data).toHaveLength(2);
    }, 10000);

    it("get test", async () => {

        const response = await request(app)
            .get('/api/v1/records')
            .query({'page':1, 'unresolved':false, 'category':'java', 'level':'easy'})

        expect(response.body.data._quizRecords).toHaveLength(5);
        expect(response.body.status).toBe(200);
    }, 10000);

    it("delete test", async () => {

        const response = await request(app)
                .delete('/api/v1/records')
                .query({ 'page':1,'deleteOption': 'all' });

        expect(response.status).toBe(200);
        expect(response.body.message).toBe('문제풀기 기록 삭제 성공 option:all');
    }, 10000);


});

