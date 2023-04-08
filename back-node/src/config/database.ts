import 'reflect-metadata';
import Container from 'typedi';
import { ConnectionOptions, createConnection, getConnectionManager, useContainer } from 'typeorm';
import { ConstraintSnakeNamingStrategy } from './ConstraintSnakeNamingStrategy';
import { env } from './env';
import { Quiz } from '../entity/quiz/Quiz';
import { QuizChoice } from '../entity/quizChoice/QuizChoice';
import { Category } from '../entity/category/Category';
export async function createDatabaseConnection(): Promise<void> {
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

  const mongoConnectionOption: ConnectionOptions = {
    name: 'mongodb',
    type: 'mongodb',
    url: env.mongoDatabase.url,
    useNewUrlParser: true,
    useUnifiedTopology: true,
    synchronize: false,
    logging: true,
    namingStrategy: new ConstraintSnakeNamingStrategy(),
  };

  useContainer(Container);

  if (!getConnectionManager().has('default')) {
    await createConnection(mysqlConnectionOption);
  }

  if (!getConnectionManager().has('mongodb')) {
    await createConnection(mongoConnectionOption);
  }
}
