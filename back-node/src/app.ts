import 'reflect-metadata';
import Container from 'typedi';
import { useContainer as routingUseContainer, useExpressServer } from 'routing-controllers';
import * as bodyParser from 'body-parser';
import { createMongoDBConnection } from './config/database';
import { ErrorHandler } from './error/handler/ErrorHandler';
import express = require('express');
import { QuizController } from './controller/quiz/QuizController';
import cors from 'cors';
import { JwtAuthorizationFilter } from './jwt/filter/JwtAuthorizationFilter';
import { RecordsController } from './controller/records/RecordsController';

export class App {
  public app;

  constructor(uri?: string) {
    this.app = express();
    this.setMiddlewares();
    this.setExpress();
    this.setDatabase(uri);
  }

  public setExpress(): void {
    try {
      routingUseContainer(Container);

      useExpressServer(this.app, {
        routePrefix: '/api/v1',
        controllers: [QuizController,RecordsController],


      });
    } catch (error) {
      throw error;
    }
  }

  public async setDatabase(uri?: string): Promise<void> {
    try {
      await createMongoDBConnection(uri);
    } catch (error) {
      throw error;
    }
  }

  private setMiddlewares(): void {
    this.app.use(bodyParser.json());
    this.app.use(bodyParser.urlencoded({ extended: false }));
    this.app.use(cors());
    this.app.use(JwtAuthorizationFilter);
    this.app.use(ErrorHandler);
  }
}
