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

import quiz.domain.category.Category;
import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.UserCategoryRepository;
import quiz.exception.DuplicateTitleException;
import quiz.exception.NullUserKeyFromJwtTokenException;
import quiz.service.category.CategoryService;
import quiz.service.category.dto.S_CategorySaveReqeust;
import quiz.service.category.dto.S_CategorySaveResponse;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceSaveTest {

    CategoryService categoryService;
    UserCategoryRepository userCategoryRepository;
    S_CategorySaveReqeust reqeust;

    Category category;

    UserCategory userCategory;
    @BeforeEach
    void init() {
        userCategoryRepository = mock(UserCategoryRepository.class);
        categoryService = new CategoryService(userCategoryRepository);
        reqeust = S_CategorySaveReqeust.builder()
            .userKey("testUser")
            .title("testTitle")
            .description("testDescription")
            .build();

        category = Category.builder().categoryName("testTitle")
            .categoryDescription("testDescription").build();

        userCategory = UserCategory.builder().userKey("testUser").build();
        userCategory.addCategory(category);
    }

    @Test
    @DisplayName("정상적인 상황")
    void saveTest() {
        // given
        when(userCategoryRepository.save(any())).thenReturn(userCategory);
        // when
        S_CategorySaveResponse response = categoryService.save(reqeust);
        // then

        assertThat(response.getUserKey()).isEqualTo("testUser");
        assertThat(response.getDescription()).isEqualTo("testDescription");
    }
    @Test
    @DisplayName("중복이 있는경우")
    void saveTestWhenDuplicate() {
        List<UserCategory> userCategoryList = new ArrayList<>();
        userCategoryList.add(userCategory);

        when(userCategoryRepository.findByUserKeyAndTitle(any(), any())).thenReturn(userCategoryList);


        Exception exception =  assertThrows(DuplicateTitleException.class, () -> {
            categoryService.save(reqeust);
        });

        assertThat(exception.getMessage()).isEqualTo("중복된 카테고리 제목은 사용할 수 없습니다.");
    }

}
