package quiz.controller;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import quiz.controller.category.CategoryController;
import quiz.controller.dto.CommonResponse;
import quiz.domain.customCategory.CustomCategory;
import quiz.service.category.CategoryService;
import quiz.service.category.dto.S_CategoryGetResponse;

@ExtendWith(MockitoExtension.class)
@DisplayName("Get Category 테스트")
public class GetCategoryControllerTest {

    private CategoryService categoryService;

    private CategoryController categoryController;

    @BeforeEach
    void init() {
        categoryService = mock(CategoryService.class);
        categoryController = new CategoryController(categoryService);
    }

    @Test
    @DisplayName("get 정상적인 요청")
    void getCategoryTest() {
        String userKey = "testUser1";
        CustomCategory category = CustomCategory.builder()
            .userKey(userKey)
            .title("testTitle1")
            .description("testDescription1")
            .build();
        CustomCategory category2 = CustomCategory.builder()
            .userKey(userKey)
            .title("testTitle2")
            .description("testDescription1")
            .build();
        CustomCategory category3 = CustomCategory.builder()
            .userKey(userKey)
            .title("testTitle3")
            .description("testDescription1")
            .build();

        List<CustomCategory> customCategoryList = new ArrayList<>();
        customCategoryList.add(category);
        customCategoryList.add(category2);
        customCategoryList.add(category3);

        S_CategoryGetResponse responseFromService = S_CategoryGetResponse.builder()
            .categories(customCategoryList)
            .build();

        when(categoryService.get(any())).thenReturn(responseFromService);

        CommonResponse<?> result = categoryController.getCategories(userKey);

        assertThat(result.getStatus()).isEqualTo(200);
        assertThat(result.getMsg()).isEqualTo("카테고리 불러오기 성공");
        assertThat(result.getData()).isEqualTo(responseFromService);
    }
}
