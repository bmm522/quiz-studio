import "reflect-metadata";
import Container from "typedi";
import { ConnectionOptions, createConnection, getConnectionManager, useContainer } from "typeorm";
import { ConstraintSnakeNamingStrategy } from "./ConstraintSnakeNamingStrategy";
import {env} from "./env";

export async function createDatabaseConnection(): Promise<void> {
    const connectionOption: ConnectionOptions = {
        type: "mysql",
        host: env.database.host,
        port: env.database.port,
        username: env.database.username,
        password: env.database.password,
        database: env.database.database,
        entities: [
            "src/entity/**/*.ts",
        ],
        synchronize: true,
        namingStrategy: new ConstraintSnakeNamingStrategy(),
    };
    useContainer(Container);
    if(!getConnectionManager().has("default")) {
        await createConnection(connectionOption);
    }
}