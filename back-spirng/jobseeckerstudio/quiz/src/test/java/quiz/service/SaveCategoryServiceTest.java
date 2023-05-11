package quiz.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
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
import quiz.exception.DuplicateTitleException;
import quiz.service.category.CategoryService;
import quiz.service.category.dto.S_CategorySaveReqeust;
import quiz.service.category.dto.S_CategorySaveResponse;

@ExtendWith(MockitoExtension.class)
public class SaveCategoryServiceTest {

    CustomCategoryRepository customCategoryRepository;
    CategoryService categoryService;
    @BeforeEach
    void init() {
        customCategoryRepository = mock(CustomCategoryRepository.class);
        categoryService = new CategoryService(customCategoryRepository);
    }

    @Test
    @DisplayName("정상적인 상황")
    void saveTest() {
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
        List<CustomCategory> customCategoryList = new ArrayList<>();
        when(customCategoryRepository.findByUserKeyAndTitle(any(), any())).thenReturn(customCategoryList);
        when(customCategoryRepository.save(any())).thenReturn(entity);

        S_CategorySaveResponse response = categoryService.save(reqeust);

        assertThat(response.getUserKey()).isEqualTo("testUser");
        assertThat(response.getTitle()).isEqualTo("testTitle");
        assertThat(response.getDescription()).isEqualTo("testDescription");
    }

    @Test
    @DisplayName("중복된 카테고리 제목이 있을때")
    void saveWhenDuplicateTitle(){
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

        CustomCategory customCategory = CustomCategory.builder().build();
        CustomCategory customCategory2 = CustomCategory.builder().build();

        List<CustomCategory> customCategoryList = new ArrayList<>();
        customCategoryList.add(customCategory);
        customCategoryList.add(customCategory2);

        when(customCategoryRepository.findByUserKeyAndTitle(any(), any())).thenReturn(customCategoryList);

        Exception exception = assertThrows(DuplicateTitleException.class, () -> {
            categoryService.save(reqeust);
        });

        assertThat(exception.getMessage()).isEqualTo("중복된 카테고리 제목은 사용할 수 없습니다.");


    }
}
