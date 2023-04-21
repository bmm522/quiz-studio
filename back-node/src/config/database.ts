import 'reflect-metadata';
import Container from 'typedi';
import { ConnectionOptions, createConnection, getConnectionManager, useContainer } from 'typeorm';
import { ConstraintSnakeNamingStrategy } from './ConstraintSnakeNamingStrategy';
import { env } from './env';
import { Quiz } from '../domain/quiz/Quiz';
import { QuizChoice } from '../domain/quizChoice/QuizChoice';
import { Category } from '../domain/category/Category';
import * as mongoose from 'mongoose';



export async function createMySQLConnection(): Promise<void> {
  const mysqlConnectionOption: ConnectionOptions = {
    name: 'default',
    type: 'mysql',
    host: env.mysqlDatabase.host,
    port: env.mysqlDatabase.port,
    username: env.mysqlDatabase.username,
    password: env.mysqlDatabase.password,
    database: env.mysqlDatabase.database,
    entities: [Quiz, QuizChoice, Category],
    synchronize: false,
    namingStrategy: new ConstraintSnakeNamingStrategy(),
  };

  useContainer(Container);
  if (!getConnectionManager().has('default')) {
    await createConnection(mysqlConnectionOption);
  }
}

export async function createMongoDBConnection(): Promise<void> {
  const mongoConnectionOption: ConnectionOptions = {
    name: 'mongodb',
    type: 'mongodb',
    url: env.mongoDatabase.url,
    useNewUrlParser: true,
    useUnifiedTopology: true,
    database: 'job_seecker_studio',
  };

  useContainer(Container);
  if (!getConnectionManager().has('mongodb')) {
    await createConnection(mongoConnectionOption);
  }
}





