package quiz.unit.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import quiz.domain.userCategory.repository.UserCategoryRepository;
import quiz.domain.userCategory.repository.dto.UserCategoryDto;
import quiz.service.userCategory.UserCategoryService;
import quiz.service.userCategory.dto.S_UserCategoryGetResponse;

@ExtendWith(MockitoExtension.class)
public class GetUserCategoryServiceTest {

    UserCategoryRepository userCategoryRepository;
    UserCategoryService userCategoryService;
    @BeforeEach
    void init() {
        userCategoryRepository = mock(UserCategoryRepository.class);
        userCategoryService = new UserCategoryService(userCategoryRepository);
    }

    @Test
    @DisplayName("정상적인 요청")
    void getTest() {
        String userKey = "testUser";
        UserCategoryDto dto1 = UserCategoryDto.builder().userKey(userKey).title("title1").description("description1").build();
        UserCategoryDto dto2 = UserCategoryDto.builder().userKey(userKey).title("title2").description("description2").build();
        UserCategoryDto dto3 = UserCategoryDto.builder().userKey(userKey).title("title3").description("description3").build();
        List<UserCategoryDto> userCategoryDtos = new ArrayList<>();
        userCategoryDtos.add(dto1);
        userCategoryDtos.add(dto2);
        userCategoryDtos.add(dto3);

        when(userCategoryRepository.findUserCategoryDtosByUserKey(any())).thenReturn(userCategoryDtos);

        S_UserCategoryGetResponse response = userCategoryService.get(userKey);

        assertThat(response.getCategories().get(0).getUserKey()).isEqualTo(userKey);
        assertThat(response.getCategories().size()).isEqualTo(3);

    }


}
// @Test
// @DisplayName("정상적인 요청")
// void getTest() {
//     String userKey = "userKey1";
//     UserCategory category = UserCategory.builder()
//         .userKey(userKey)
//         .title("testTitle1")
//         .description("testDescription1")
//         .build();
//     UserCategory category2 = UserCategory.builder()
//         .userKey(userKey)
//         .title("testTitle2")
//         .description("testDescription1")
//         .build();
//     UserCategory category3 = UserCategory.builder()
//         .userKey(userKey)
//         .title("testTitle3")
//         .description("testDescription1")
//         .build();
//
//     List<UserCategory> userCategoryList = new ArrayList<>();
//     userCategoryList.add(category);
//     userCategoryList.add(category2);
//     userCategoryList.add(category3);
//
//     when(userCategoryRepository.findByUserKey(userKey)).thenReturn(userCategoryList);
//
//     S_UserCategoryGetResponse result = categoryService.get(userKey);
//     assertThat(result.getCategories().size()).isEqualTo(3);
// }