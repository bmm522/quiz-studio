import { QuizParams } from '../../../controller/quiz/dto/QuizParams';
import { QuizListItem } from '../dto/QuizListItem';


export class QuizServiceMapper {
  static toQuizListRequest(params: QuizParams): QuizListItem {
    return QuizListItem.create(params.category, params.level);
  }

  // static async toQuizResponse(data: Quiz[]): Promise<QuizResponse[]> {
  //   const result: QuizResponse[] = data.map(quiz => {
  //     const choices = quiz.quizChoices.map(choice => {
  //       return { content: choice.choiceContent, isAnswer: choice.isAnswer };
  //     });
  //     return new QuizResponse(quiz.quizTitle, choices);
  //   });
  //   return result;
  // }
}
