package quiz.repository;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import quiz.domain.category.Category;
import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.UserCategoryRepository;
import quiz.domain.userCategory.repository.dto.UserCategoryDto;

@DataJpaTest
public class UserCategoryQueryRepositoryTest {

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @BeforeEach
    void init() {
        Category category = Category.builder()
            .categoryName("testCategoryName")
            .categoryDescription("testCategoryDescription")
            .build();

        UserCategory userCategory = UserCategory.builder()
            .userKey("testUser")
            .category(category)
            .build();



        Category category2 = Category.builder()
            .categoryName("testCategoryName2")
            .categoryDescription("testCategoryDescription2")
            .build();

        UserCategory userCategory2 = UserCategory.builder()
            .userKey("testUser")
            .category(category2)
            .build();



        userCategoryRepository.save(userCategory);
        userCategoryRepository.save(userCategory2);
    }

    @Test
    @DisplayName("userKey, title로 UserCategory 객체 찾아오기")
    void findUserCategoriesByUserKeyAndTitleTest() {
       UserCategory  userCategory = userCategoryRepository.findUserCategoryByUserKeyAndTitle("testUser", "testCategoryName").get();

        assertThat(userCategory.getUserKey()).isEqualTo("testUser");
        assertThat(userCategory.getCategoryName()).isEqualTo("testCategoryName");
        assertThat(userCategory.getCategoryDescription()).isEqualTo("testCategoryDescription");
    }

    @Test
    @DisplayName("userKey로 해당 UserCategory 가져온 후 UserCategoryDto로 반환")
    void findUserCategoryDtosByUserKeyTest() {
        List<UserCategoryDto> userCategoryDtos = userCategoryRepository.findUserCategoryDtosByUserKey("testUser");

        assertThat(userCategoryDtos.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("id로 UserCategory 객체 가져오기 ")
    void  findUserCategoryByUserCategoryId() {
        Category category3 = Category.builder()
            .categoryName("testCategoryName3")
            .categoryDescription("testCategoryDescription3")
            .build();

        UserCategory userCategory3 = UserCategory.builder()
            .userKey("testUser")
            .category(category3)
            .build();
        UserCategory savedData = userCategoryRepository.save(userCategory3);
        UserCategory userCategory = userCategoryRepository.findUserCategoryByUserCategoryId(savedData.getUserCategoryId()).get();


        assertThat(userCategory.getCategoryName()).isEqualTo("testCategoryName3");

    }
}
