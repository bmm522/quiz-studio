import "reflect-metadata";
import Container from "typedi";
import { ConnectionOptions, createConnection, getConnectionManager, useContainer } from "typeorm";
import { ConstraintSnakeNamingStrategy } from "./ConstraintSnakeNamingStrategy";
import {env} from "./env";
import {Quiz} from "../entity/quiz/Quiz";
import {QuizChoice} from "../entity/quizChoice/QuizChoice";
import {Category} from "../entity/category/Category";

export async function createDatabaseConnection(): Promise<void> {
    const connectionOption: ConnectionOptions = {
        type: "mysql",
        host: env.database.host,
        port: env.database.port,
        username: env.database.username,
        password: env.database.password,
        database: env.database.database,
        entities: [
            Quiz,
            QuizChoice,
            Category
        ],
        synchronize: false,
        namingStrategy: new ConstraintSnakeNamingStrategy(),
    };
    useContainer(Container);
    if(!getConnectionManager().has("default")) {
        await createConnection(connectionOption);
    }
}