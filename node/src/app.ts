import express from 'express';
import Container from "typedi";
import { useContainer as routingUseContainer, useExpressServer } from "routing-controllers";
import * as bodyParser from "body-parser";
import path from "path";
import {IndexController} from "./controller/IndexController";

export class App {

  public app;

  constructor() {
    this.app = express();
    this.app.set('view', path.join(__dirname, '../view/ejs'));
    this.app.set('view engine', 'ejs');
    this.setMiddlewares();

  }

  private setExpress(): void {
    try {
      routingUseContainer(Container);
      useExpressServer(this.app, {
        routePrefix: "/api/v1",
        controllers: [IndexController]
      })
    }catch (error) {
      console.log(error);
    }
  }

  public async setDatabase(): Promise<void> {
    try {
      await createDatabaseConnection();
    } catch (error) {
      logger.error(error);
      throw error;
    }
  }

  private setMiddlewares(): void {
    this.app.use(bodyParser.json());
    this.app.use(bodyParser.urlencoded({ extended: false }));
  }
}