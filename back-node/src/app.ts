import "reflect-metadata";
import Container from "typedi";
import { useContainer as routingUseContainer, useExpressServer } from "routing-controllers";
import * as bodyParser from "body-parser";
import {createDatabaseConnection} from "./config/database";
import {ErrorHandler} from "./error/handler/ErrorHandler"
import { JwtAuthorizationFilter } from './jwt/JwtAuthorizationFilter';
import express = require("express");
import {QuizController} from "./controller/quiz/QuizController";

export class App {

  public app;

  constructor() {
    this.app = express();
    this.setMiddlewares();
    this.setExpress();
    this.setDatabase();

  }

  private setExpress(): void {
    try {
      routingUseContainer(Container);

      useExpressServer(this.app, {
        routePrefix: "/api/v1",
        controllers: [QuizController],
      })
    }catch (error) {
      console.log(error);
    }
  }

  public async setDatabase(): Promise<void> {
    try {
      await createDatabaseConnection();
    } catch (error) {
      throw error;
    }
  }

  private setMiddlewares(): void {
    this.app.use(bodyParser.json());
    this.app.use(bodyParser.urlencoded({ extended: false }));
   // this.app.use(JwtAuthorizationFilter);
    this.app.use(ErrorHandler);
  }
}