package quiz.controller;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import quiz.controller.category.CategoryController;
import quiz.controller.category.dto.C_CategorySaveRequest;
import quiz.controller.dto.CommonResponse;
import quiz.exception.InvalidParameterFromDtoException;
import quiz.service.category.CategoryService;
import quiz.service.category.dto.S_CategorySaveResponse;

@ExtendWith(MockitoExtension.class)
@DisplayName("Save Category 테스트")
public class SaveCategoryControllerTest {

    private CategoryService categoryService;

    private CategoryController categoryController;

    @BeforeEach
    void init() {
        categoryService = mock(CategoryService.class);
        categoryController = new CategoryController(categoryService);
    }

    @Test
    @DisplayName("save 정상적인 요청")
    void saveCategoryTest() {

        C_CategorySaveRequest request = C_CategorySaveRequest.builder()
            .title("test")
            .description("testtest")
            .build();
        String userKey = "testUserKey";

        S_CategorySaveResponse response = S_CategorySaveResponse.builder()
            .userKey("testUserKey")
            .title("test")
            .description("testtest")
            .build();

        when(categoryService.save(any())).thenReturn(response);

        CommonResponse<?> result = categoryController.saveCategory(userKey, request);

        assertThat(result.getStatus()).isEqualTo(201);
        assertThat(result.getMsg()).isEqualTo("카테고리 저장 성공");
        assertThat(result.getData()).isEqualTo(response);
    }
    @Test
    @DisplayName("save 파라미터 title 안들어왔을때")
    void saveCategoryWhenNotHaveTitleParameterRequest() {
        CategoryService categoryService = mock(CategoryService.class);
        C_CategorySaveRequest request = C_CategorySaveRequest.builder()
            .build();
        String userKey = "testUserKey";

        Exception exception = assertThrows(InvalidParameterFromDtoException.class, () -> {
            categoryController.saveCategory(userKey, request);
        });

        assertThat(exception.getMessage()).isEqualTo("save 요청에 title이 담기지 않았습니다.");
    }

    @Test
    @DisplayName("save 파라미터 description 안들어왔을때")
    void saveCategoryWhenNotHaveDescriptionParameterRequest() {
        CategoryService categoryService = mock(CategoryService.class);
        C_CategorySaveRequest request = C_CategorySaveRequest.builder()
            .title("testTitle")
            .build();
        String userKey = "testUserKey";

        Exception exception = assertThrows(InvalidParameterFromDtoException.class, () -> {
            categoryController.saveCategory(userKey, request);
        });

        assertThat(exception.getMessage()).isEqualTo("save 요청에 description이 담기지 않았습니다.");
    }

}
