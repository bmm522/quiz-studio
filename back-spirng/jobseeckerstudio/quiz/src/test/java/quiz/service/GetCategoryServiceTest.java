// package quiz.service;
//
// import static org.assertj.core.api.AssertionsForClassTypes.*;
// import static org.mockito.Mockito.*;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.junit.jupiter.MockitoExtension;
//
// import quiz.domain.userCategory.UserCategory;
// import quiz.domain.userCategory.repository.UserCategoryRepository;
// import quiz.service.category.CategoryService;
// import quiz.service.category.dto.S_CategoryGetResponse;
//
// @ExtendWith(MockitoExtension.class)
// public class GetCategoryServiceTest {
//
//     UserCategoryRepository userCategoryRepository;
//     CategoryService categoryService;
//     @BeforeEach
//     void init() {
//         userCategoryRepository = mock(UserCategoryRepository.class);
//         categoryService = new CategoryService(userCategoryRepository);
//     }
//
//     @Test
//     @DisplayName("정상적인 요청")
//     void getTest() {
//         String userKey = "userKey1";
//         UserCategory category = UserCategory.builder()
//             .userKey(userKey)
//             .title("testTitle1")
//             .description("testDescription1")
//             .build();
//         UserCategory category2 = UserCategory.builder()
//             .userKey(userKey)
//             .title("testTitle2")
//             .description("testDescription1")
//             .build();
//         UserCategory category3 = UserCategory.builder()
//             .userKey(userKey)
//             .title("testTitle3")
//             .description("testDescription1")
//             .build();
//
//         List<UserCategory> userCategoryList = new ArrayList<>();
//         userCategoryList.add(category);
//         userCategoryList.add(category2);
//         userCategoryList.add(category3);
//
//         when(userCategoryRepository.findByUserKey(userKey)).thenReturn(userCategoryList);
//
//         S_CategoryGetResponse result = categoryService.get(userKey);
//         assertThat(result.getCategories().size()).isEqualTo(3);
//     }
// }
