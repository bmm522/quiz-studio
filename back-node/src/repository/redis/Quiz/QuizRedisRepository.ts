import { Service } from "typedi";
import Redis from "ioredis";
import { env } from "../../../config/env";

@Service()
export class QuizRedisRepository {


    key: string = "test";
    data: string = "ggg";

    async findByCategoryNameAndDifficulty(

        categoryName: string,
        difficulty: string
    ) {
        const redisClient = new Redis({
            host: env.redis.host as string,
            port: parseInt(env.redis.port as string),
            username: env.redis.username as string,
            password: env.redis.password as string,
        });
        const redisKey = `${categoryName}_${difficulty}`;
        let redisValue;
        let redisValue2;

        try {
            redisValue = await redisClient.keys('*');
            redisValue2 = await redisClient.hgetall('quiz:java_easy7');
            console.log(redisValue.length);
            console.log(redisValue);
            console.log(redisValue2);
            // console.log("redisdata : " + redisData);
        } catch (err) {
            console.error(err);
        } finally {
            await redisClient.quit(); // Redis 클라이언트 명시적으로 닫아줌
        }
    }
}