// import { ControllerGetQuizRequest } from "../../../src/controller/quiz/dto/ControllerGetQuizRequest";
// import { QuizQueryRedisRepository } from "../../../src/repository/quiz/QuizQueryRedisRepository";
// import { QuizService } from "../../../src/service/quiz/QuizService";
//
// describe('QuizService Test', () => {
//     let quizService: QuizService;
//
//
//     beforeEach(() => {
//         const quizQueryRepository = new QuizQueryRedisRepository();
//         quizService = new QuizService(quizQueryRepository);
//     });
//     it('getQuizList Test', async () => {
//         const controllerGetQuizRequest = new ControllerGetQuizRequest('java', 'easy');
//
//         const result = await quizService.getQuizList(controllerGetQuizRequest);
//
//         console.log(result);
//     });
// });
