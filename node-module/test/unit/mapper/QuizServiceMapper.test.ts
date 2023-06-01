
import { ControllerGetQuizRequest } from '../../../src/controller/quiz/dto/ControllerGetQuizRequest';
import { QuizServiceMapper } from '../../../src/service/quiz/mapper/QuizServiceMapper';
import { ServiceGetQuizRequest } from '../../../src/service/quiz/dto/ServiceGetQuizRequest';

describe('QuizServiceMapperTest', () => {
  it('toGetRequest Test', () => {
    // Arrange
    const category: string = "java";
    // const level: Level = Level.EASY;

    const controllerRequest = new ControllerGetQuizRequest(category);

    // Act
    const serviceRequest = QuizServiceMapper.toGetRequest(controllerRequest);

    // Assert
    expect(serviceRequest).toBeInstanceOf(ServiceGetQuizRequest);
    expect(serviceRequest.category).toEqual(category);
  });
});
