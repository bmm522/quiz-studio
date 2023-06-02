
import { ControllerGetQuizRequest } from '../../../src/controller/quiz/dto/ControllerGetQuizRequest';
import { ServiceQuizMapper } from '../../../src/service/quiz/mapper/ServiceQuizMapper';
import { ServiceGetQuizRequest } from '../../../src/service/quiz/dto/ServiceGetQuizRequest';

describe('QuizServiceMapperTest', () => {
  it('toGetRequest Test', () => {
    // Arrange
    const category: string = "java";
    // const level: Level = Level.EASY;

    const controllerRequest = new ControllerGetQuizRequest(category);

    // Act
    const serviceRequest = ServiceQuizMapper.toGetRequest(controllerRequest);

    // Assert
    expect(serviceRequest).toBeInstanceOf(ServiceGetQuizRequest);
    expect(serviceRequest.category).toEqual(category);
  });
});
