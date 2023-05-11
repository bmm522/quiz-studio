import { App } from '../../src/app';
import express, { NextFunction } from 'express';
import 'reflect-metadata';
import { JwtAuthorizationFilter } from '../../src/jwt/filter/JwtAuthorizationFilter';
import { UserKeyRequest } from '../../src/jwt/dto/UserKeyRequest';
import request from 'supertest';
import http from 'http';
import { env } from '../../src/config/env';

jest.mock('../../src/jwt/filter/JwtAuthorizationFilter');

describe('QuizController', () => {
  if (env.nodeEnv.testEnv === 'development') {
    let app: express.Application;
    let server: http.Server;

    beforeAll(async () => {
      app = new App().app;
      (JwtAuthorizationFilter as jest.Mock).mockImplementation(
        (req: UserKeyRequest, res: Response, next: NextFunction) => {
          req.userKey = 'user1';
          next();
        },
      );

      server = app.listen(0);
    });

    afterAll(async () => {
      await server.close();
    });

    it('get test', async () => {
      const response = await request(app)
        .get('/api/v1/quiz')
        .query({ category: 'java', level: 'easy' });

      expect(response.body.data).toHaveLength(10);
      expect(response.body.status).toBe(200);
      expect(response.body.message).toBe('퀴즈 목록 불러오기 성공');
    });
  } else {
    it.skip('get test', () => {});
  }
});
