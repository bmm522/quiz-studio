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
import quiz.service.userCategory.UserCategoryService;
import quiz.service.userCategory.dto.S_UserCategorySaveReqeust;
import quiz.service.userCategory.dto.S_UserCategorySaveResponse;

@ExtendWith(MockitoExtension.class)
public class UserCategoryServiceSaveTest {

    UserCategoryService userCategoryService;
    UserCategoryRepository userCategoryRepository;
    S_UserCategorySaveReqeust reqeust;

    Category category;

    UserCategory userCategory;
    @BeforeEach
    void init() {
        userCategoryRepository = mock(UserCategoryRepository.class);
        userCategoryService = new UserCategoryService(userCategoryRepository);
        reqeust = S_UserCategorySaveReqeust.builder()
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
        S_UserCategorySaveResponse response = userCategoryService.save(reqeust);
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
            userCategoryService.save(reqeust);
        });

        assertThat(exception.getMessage()).isEqualTo("중복된 카테고리 제목은 사용할 수 없습니다.");
    }

}
