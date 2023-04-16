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
        console.log(categoryName);
        console.log(difficulty);
        const redisKey = `${categoryName}_${difficulty}`;
        let redisValue;
        let redisData;

        try {
            redisValue = await redisClient.keys('*');
            redisData = await redisClient.hgetall('quiz');
            console.log(redisValue.length);
            console.log(redisValue);
            console.log("redisdata : " + redisData);
        } catch (err) {
            console.error(err);
        } finally {
            await redisClient.quit(); // Redis 클라이언트 명시적으로 닫아줌
        }
    }
}