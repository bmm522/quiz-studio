package quiz.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import quiz.domain.customCategory.CustomCategory;
import quiz.domain.customCategory.repository.CustomCategoryRepository;
import quiz.service.category.CategoryService;
import quiz.service.category.dto.S_CategorySaveReqeust;
import quiz.service.category.dto.S_CategorySaveResponse;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Test
    @DisplayName("정상적인 상황")
    void saveTest() {
        CustomCategoryRepository customCategoryRepository = mock(CustomCategoryRepository.class);
        CategoryService categoryService = new CategoryService(customCategoryRepository);

        S_CategorySaveReqeust reqeust = S_CategorySaveReqeust.builder()
            .userKey("testUser")
            .title("testTitle")
            .description("testDscription")
            .build();

        CustomCategory entity = CustomCategory.builder()
            .userKey("testUser")
            .title("testTitle")
            .description("testDescription")
            .build();

        when(customCategoryRepository.save(any())).thenReturn(entity);

        S_CategorySaveResponse response = categoryService.save(reqeust);

        assertThat(response.getUserKey()).isEqualTo("testUser");
        assertThat(response.getTitle()).isEqualTo("testTitle");
        assertThat(response.getDescription()).isEqualTo("testDescription");
    }
}
