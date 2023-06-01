const path = require('path');
const dotenv = require('dotenv');

const envPath = path.resolve(__dirname, '../../env/.env');
const config = dotenv.config({ path: envPath });

if (config.error) {
  throw config.error;
}

/**
 * 환경 변수
 */
export const env = {
  nodeEnv: {
    testEnv: process.env.TEST_ENV,
  },

  openAI: {
    secretKey: process.env.OPEN_API_KEY,
    organization:process.env.OPEN_ORGANIZATION,
  },
  // mysqlDatabase: {
  //   host: process.env.TYPEORM_MYSQL_HOST,
  //   port: Number(process.env.TYPEORM_MYSQL_PORT),
  //   username: process.env.TYPEORM_MYSQL_USERNAME,
  //   password: process.env.TYPEORM_MYSQL_PASSWORD,
  //   database: process.env.TYPEORM_MYSQL_DATABASE,
  //   synchronize: process.env.TYPEORM_SYNCHRONIZE === 'true',
  //   logging: process.env.TYPEORM_LOGGING === 'true',
  //   dropSchema: process.env.TYPEORM_DROP_SCHEMA === 'true',
  // },
  mongoDatabase: {
    url: process.env.MONGO_URL,
  },
  redis: {
    host: process.env.REDIS_HOST,
    port: process.env.REDIS_PORT,
    username: process.env.REDIS_USERNAME,
    password: process.env.REDIS_PASSWORD,
  },
};

export const envJwt: { secretKey: string; tokenPrefix: string; refreshPrefix: string } = {
  secretKey: process.env.SECRET_KEY!,
  tokenPrefix: process.env.TOKEN_PREFIX!,
  refreshPrefix: process.env.REFRESH_PREFIX!,
};
