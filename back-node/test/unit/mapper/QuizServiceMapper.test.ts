import { CategoryEnum } from '../../../src/global/enum/CategoryEnum';
import { Level } from '../../../src/global/enum/Level';
import { ControllerGetQuizRequest } from '../../../src/controller/quiz/dto/ControllerGetQuizRequest';
import { QuizServiceMapper } from '../../../src/service/quiz/mapper/QuizServiceMapper';
import { ServiceGetQuizRequest } from '../../../src/service/quiz/dto/ServiceGetQuizRequest';

describe('QuizServiceMapperTest', () => {
  it('toGetRequest Test', () => {
    // Arrange
    const category: CategoryEnum = CategoryEnum.JAVA;
    const level: Level = Level.EASY;

    const controllerRequest = new ControllerGetQuizRequest(category, level);

    // Act
    const serviceRequest = QuizServiceMapper.toGetRequest(controllerRequest);

    // Assert
    expect(serviceRequest).toBeInstanceOf(ServiceGetQuizRequest);
    expect(serviceRequest.category).toEqual(category);
    expect(serviceRequest.level).toEqual(level);
  });
});
