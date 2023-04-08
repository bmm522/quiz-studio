import * as path from 'path';

const config = require('dotenv').config({
  path: path.join(__dirname, '../../env/.env'),
});

if (config.error) {
  throw config.error;
}

/**
 * 환경 변수
 */
export const env = {
  mysqlDatabase: {
    host: process.env.TYPEORM_MYSQL_HOST,
    port: Number(process.env.TYPEORM_MYSQL_PORT),
    username: process.env.TYPEORM_MYSQL_USERNAME,
    password: process.env.TYPEORM_MYSQL_PASSWORD,
    database: process.env.TYPEORM_MYSQL_DATABASE,
    synchronize: process.env.TYPEORM_SYNCHRONIZE === 'true',
    logging: process.env.TYPEORM_LOGGING === 'true',
    dropSchema: process.env.TYPEORM_DROP_SCHEMA === 'true',
  },
  mongoDatabase: {
    url: process.env.TYPEORM_MONGO_HOST,
  },
};

export const envJwt: { secretKey: string; tokenPrefix: string; refreshPrefix: string } = {
  secretKey: process.env.SECRET_KEY!,
  tokenPrefix: process.env.TOKEN_PREFIX!,
  refreshPrefix: process.env.REFRESH_PREFIX!,
};
