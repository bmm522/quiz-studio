//
// import { Quiz } from '../../domain/quiz/Quiz';
// import { Category } from '../../domain/category/Category';
// import { CategoryEnum } from '../../global/enum/CategoryEnum';
// import { Level } from '../../global/enum/Level';
// import { QuizQueryRDBMSRepository } from './rdbms/QuizQueryRDBMSRepository';
//
// @EntityRepository(Quiz)
// export class QuizQueryMySQLRepository {
//   async getQuizRandomList(category: CategoryEnum, level: Level): Promise<Quiz[] | null> {
//     const result = await createQueryBuilder()
//       .select('quiz')
//       .from(Quiz, 'quiz')
//       .leftJoinAndSelect('quiz.category', 'category')
//       .leftJoinAndSelect('quiz.quizChoices', 'quizChoice')
//       .where('category.category_name = :categoryName')
//       .andWhere('quiz.difficulty = :difficulty')
//       .orderBy('RAND()')
//       .skip(0) // 첫 번째 객체부터 시작
//       .take(10) // 최대 10개의 객체만 선택
//       .setParameters({ categoryName: category, difficulty: level })
//       .getMany();
//
//     return result;
//   }
// }
