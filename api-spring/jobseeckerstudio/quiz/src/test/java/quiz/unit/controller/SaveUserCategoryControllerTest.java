package quiz.unit.controller;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import quiz.controller.userCategory.UserCategoryController;
import quiz.controller.userCategory.dto.C_UserCategorySaveRequest;
import quiz.controller.dto.CommonResponse;
import quiz.exception.ExistCategorySaveException;
import quiz.exception.InvalidParameterFromDtoException;
import quiz.service.userCategory.UserCategoryService;
import quiz.service.userCategory.dto.S_UserCategorySaveResponse;

@ExtendWith(MockitoExtension.class)
@DisplayName("Save Category 테스트")
public class SaveUserCategoryControllerTest {

    private UserCategoryService userCategoryService;

    private UserCategoryController userCategoryController;

    @BeforeEach
    void init() {
        userCategoryService = mock(UserCategoryService.class);
        userCategoryController = new UserCategoryController(userCategoryService);
    }

    @Test
    @DisplayName("save 정상적인 요청")
    void saveCategoryTest() {

        C_UserCategorySaveRequest request = C_UserCategorySaveRequest.builder()
            .title("test")
            .description("testtest")
            .build();
        String userKey = "testUserKey";

        S_UserCategorySaveResponse response = S_UserCategorySaveResponse.builder()
            .userKey("testUserKey")
            .title("test")
            .description("testtest")
            .build();

        when(userCategoryService.save(any())).thenReturn(response);

        CommonResponse<?> result = userCategoryController.saveCategory(userKey, request);

        assertThat(result.getStatus()).isEqualTo(201);
        assertThat(result.getMsg()).isEqualTo("카테고리 저장 성공");
        assertThat(result.getData()).isEqualTo(response);
    }
    @Test
    @DisplayName("save 파라미터 title 안들어왔을때")
    void saveCategoryWhenNotHaveTitleParameterRequest() {
        UserCategoryService userCategoryService = mock(UserCategoryService.class);
        C_UserCategorySaveRequest request = C_UserCategorySaveRequest.builder()
            .build();
        String userKey = "testUserKey";

        Exception exception = assertThrows(InvalidParameterFromDtoException.class, () -> {
            userCategoryController.saveCategory(userKey, request);
        });

        assertThat(exception.getMessage()).isEqualTo("save 요청에 title이 담기지 않았습니다.");
    }

    @Test
    @DisplayName("save 파라미터 description 안들어왔을때")
    void saveCategoryWhenNotHaveDescriptionParameterRequest() {
        UserCategoryService userCategoryService = mock(UserCategoryService.class);
        C_UserCategorySaveRequest request = C_UserCategorySaveRequest.builder()
            .title("testTitle")
            .build();
        String userKey = "testUserKey";

        Exception exception = assertThrows(InvalidParameterFromDtoException.class, () -> {
            userCategoryController.saveCategory(userKey, request);
        });

        assertThat(exception.getMessage()).isEqualTo("save 요청에 description이 담기지 않았습니다.");
    }

    @Test
    @DisplayName("기존 카테고리 이름으로 요청을 보냈을 때 (자바)")
    void saveCategoryWhenExistCategoryWithJava() {
        UserCategoryService userCategoryService = mock(UserCategoryService.class);
        C_UserCategorySaveRequest request = C_UserCategorySaveRequest.builder()
            .title("java")
            .description("testDescription")
            .build();
        String userKey = "testUserKey";

        Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
            userCategoryController.saveCategory(userKey, request);
        });

        assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
    }
    @Test
    @DisplayName("기존 카테고리 이름으로 요청을 보냈을 때(자료구조)")
    void saveCategoryWhenExistCategoryWithDataStructure() {
        UserCategoryService userCategoryService = mock(UserCategoryService.class);
        C_UserCategorySaveRequest request = C_UserCategorySaveRequest.builder()
            .title("Data Structure")
            .description("testDescription")
            .build();
        String userKey = "testUserKey";

        Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
            userCategoryController.saveCategory(userKey, request);
        });

        assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("기존 카테고리 이름으로 요청을 보냈을 때(공백포함)")
    void saveCategoryWhenExistCategoryWithBlank() {
        UserCategoryService userCategoryService = mock(UserCategoryService.class);
        C_UserCategorySaveRequest request = C_UserCategorySaveRequest.builder()
            .title("  DAta Str Uct Ure   ")
            .description("testDescription")
            .build();
        String userKey = "testUserKey";

        Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
            userCategoryController.saveCategory(userKey, request);
        });

        assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
    }

}
