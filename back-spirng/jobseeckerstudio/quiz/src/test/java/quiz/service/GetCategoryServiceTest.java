package quiz.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import quiz.domain.customCategory.CustomCategory;
import quiz.domain.customCategory.repository.CustomCategoryRepository;
import quiz.service.category.CategoryService;
import quiz.service.category.dto.S_CategoryGetResponse;

@ExtendWith(MockitoExtension.class)
public class GetCategoryServiceTest {

    CustomCategoryRepository customCategoryRepository;
    CategoryService categoryService;
    @BeforeEach
    void init() {
        customCategoryRepository = mock(CustomCategoryRepository.class);
        categoryService = new CategoryService(customCategoryRepository);
    }

    @Test
    @DisplayName("정상적인 요청")
    void getTest() {
        String userKey = "userKey1";
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

        when(customCategoryRepository.findByUserKey(userKey)).thenReturn(customCategoryList);

        S_CategoryGetResponse result = categoryService.get(userKey);
        assertThat(result.getCategories().size()).isEqualTo(3);
    }
}
