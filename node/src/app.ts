import express from 'express';
import Container from "typedi";
import { useContainer as routingUseContainer, useExpressServer } from "routing-controllers";
import * as bodyParser from "body-parser";
import path from "path";
import {IndexController} from "./controller/index/IndexController";
import {createDatabaseConnection} from "./config/database";
import {QuestionController} from "./controller/question/QuestionController";

export class App {

  public app;

  constructor() {
    this.app = express();
    this.app.set('views', path.join(__dirname, 'view/ejs'));
    this.app.set('view engine', 'ejs');

    this.setExpress();
    this.setMiddlewares();

  }

  private setExpress(): void {
    try {
      routingUseContainer(Container);
      useExpressServer(this.app, {
        routePrefix: "/api/v1",
        controllers: [IndexController, QuestionController]
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
    // CSS 및 JavaScript 파일에 대한 경로 설정
    this.app.use('/css', express.static(path.join(__dirname, 'view/ejs/css')));
    this.app.use('/js', express.static(path.join(__dirname, 'view/ejs/js')));
    this.app.use('/image', express.static(path.join(__dirname, 'view/ejs/image')));

    // CSS 및 JavaScript 파일의 MIME 유형 설정
    this.app.use('*.css', (req, res, next) => {
      res.set('Content-Type', 'text/css');
      next();
    });
    this.app.use('*.js', (req, res, next) => {
      res.set('Content-Type', 'text/javascript');
      next();
    });
  }
}